package com.vognev.textilewebproject.service;


import com.vognev.textilewebproject.dto.*;
import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.util.Constants;
import com.vognev.textilewebproject.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

/**
 * textilewebproject  18/09/2021-15:03
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private MyMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostOfficeService postOfficeService;

    @Autowired
    private EntityManager em;

    @Value("${hostname}")
    private String hostname;

    private static int  START_LIST=0;
    private static int  SIZE_USER_LIST=0;
    private static int  PAGE_NUM=0;
    private static double  SIZE_USER_PAGE=0;

    @Override
    public UserDetails loadUserByUsername(String  username) throws UsernameNotFoundException {

        MyUser myUser=myUserRepository.findByUsername(username);

        if(myUser==null){

            throw new UsernameNotFoundException("User not found");
        }
        return myUser;
    }


    public boolean addUser(MyUser myUser){

        MyUser userFromDb = myUserRepository.findByUsername(myUser.getUsername());

        if (userFromDb != null) {

            return false;
        }
        myUser.setActive(true);
        myUser.setRoles(Collections.singleton(Role.USER));
        myUser.setActivationCode(UUID.randomUUID().toString());
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));

        save(myUser);

        sendUser(save(myUser));//может мешать антивирус и быть заблокированны поля  провайдером
        return true;
    }


    public MyUser save(MyUser user){
        return myUserRepository.save(user);
    }


    public Map<String,String> addUserFromAdmin(
            MyUser userDto,
            String info,
            String phone,
            String region,
            String district,
            String city,
            String address,
            String postCode,
            String postOffice
    ){
        MyUser myUser=new MyUser();

        StringBuilder tempEmail= new StringBuilder();

        Map<String,String> errorMessage= new HashMap<>();

        if(phone.length()>4){

            if (phoneService.getNumberPhone(phone) != null) {

                errorMessage.put("phoneError","Номер вже існує в базі");
            }
        }
        if (myUserRepository.findByUsername(userDto.getUsername())!=null) {

            errorMessage.put("usernameError","Такий логін вже існує");
        }
        if(userDto.getEmail().length()>3) {

            if (myUserRepository.findByEmail(userDto.getEmail()) != null) {

                errorMessage.put("emailError", "Такий email вже існує");

            }else{

                tempEmail.append(userDto.getEmail());
            }
        }else{

            tempEmail.append(colUsers()+1);
            tempEmail.append("@");
            tempEmail.append(colUsers()+1);
            tempEmail.append(".temp");
        }
        if(errorMessage.size()==0){

            String pass=userDto.getUsername()+String.valueOf(colUsers()+1);//"T3N2v@"

            myUser.setName(userDto.getName());
            myUser.setUsername(userDto.getUsername());
            myUser.setEmail(tempEmail.toString());
            myUser.setActive(true);
            myUser.setRoles(Collections.singleton(Role.USER));
            myUser.setActivationCode(UUID.randomUUID().toString());
            myUser.setPassword(passwordEncoder.encode(pass));
            myUser.setInfo(info);
            myUser.setRating(100);

            MyUser newUser= myUserRepository.save(myUser);

            phoneService.saveUserPhone(phone,newUser);

            addressService.saveUserAddress(region,district,city,address,postCode,newUser);

            postOfficeService.saveUserPostOffice(postOffice,newUser);

        }
        sendUser(myUser);//может мешать антивирус и быть заблокированны поля  провайдером
        return errorMessage;
    }


    private void sendUser(MyUser myUser) {

        if(!ObjectUtils.isEmpty(myUser.getEmail())){

            String message=String.format(
                    "Hello, %s \n"+
                            "Welcome to Textile. Please visit next link: http://%s/activate/%s",

                    myUser.getUsername(),
                    hostname,
                    myUser.getActivationCode()
            );
            mailSender.send(myUser.getEmail(),"Activation code ", message);
        }
    }

    public void sendAdmin(BasketDto basketDto) {
        List<MyUser> myUsers =myUserRepository.findAll();
        for(MyUser us:myUsers){
            for(Role rl:us.getRoles()){
                if(rl.name().contains("ADMIN")){

                    String message=String.format(
                        "Hello,  \n"+ " You have new order user phone: %s \n"+
                                "and name: %s",

                       basketDto.getPhone(),
                       basketDto.getName()
                );
                mailSender.send(us.getEmail(),"New order ", message);
                    System.out.println(rl.name());
                }
            }
        }
        System.out.println("oj");
    }


    public boolean activateUser(String code) {

        MyUser myUser = myUserRepository.findByActivationCode(code);

        if(myUser==null){

            return false;
        }
        myUser.setActivationCode(null);

        myUserRepository.save(myUser);

        return true;
    }


    public void saveUser(MyUser user, String username, Map<String,String> form) {

        user.setUsername(username);

        Set<String> roles= Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for(String key :form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        myUserRepository.save(user);
    }


    public List<MyUser> findAll() {
        return myUserRepository.findAll();
    }


    public void updateProfile(
                              String username,
                              String name,
                              String password,
                              String email,
                              String info,
                              MyUser user
    ) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null&& !email.equals(userEmail))||(userEmail != null && !userEmail.equals(email));

        if(isEmailChanged){

            user.setEmail(email);

            if(!ObjectUtils.isEmpty((email))){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if(!ObjectUtils.isEmpty(password)){

            user.setPassword(passwordEncoder.encode(password));
        }

        if(!ObjectUtils.isEmpty(username)){

                    user.setUsername(username);
        }

        if(!ObjectUtils.isEmpty(name)){

                    user.setName(name);

        }

        if(!ObjectUtils.isEmpty(info)){

            user.setInfo(info);
        }

        myUserRepository.save(user);

        if(isEmailChanged){

            sendUser(user);
        }
    }


    public Integer colUsers(){
        return myUserRepository.findAll().size();
    }


    public MyUser getUser(Long id){
        return myUserRepository.getById(id);
    }


    public UserDto getUserToOrder(Long id){

        MyUser myUser= myUserRepository.getById(id);

        List<PhoneUser> phoneUsers= new ArrayList<>(myUser.getPhones());

        List<PhoneUserDto> phoneUserToOrderDtoList=new ArrayList<>();

        for(PhoneUser phoneUser:phoneUsers){

            PhoneUserDto phoneUserToOrderDto =new PhoneUserDto(phoneUser.getId(),phoneUser.getPhone(),phoneUser.isActive());

            phoneUserToOrderDtoList.add(phoneUserToOrderDto);
        }
        List<AddressUser>addressUserList=new ArrayList<>(myUser.getAddresses());

        List<AddressUserDto>addressToOrderDtoList = new ArrayList<>();

        for(AddressUser addressUser:addressUserList){

            AddressUserDto addressToOrderDto= new AddressUserDto(
                    addressUser.getId(),
                    addressUser.getRegion(),
                    addressUser.getDistrict(),
                    addressUser.getCity(),
                    addressUser.getAddress(),
                    addressUser.getPostCode(),
                    addressUser.isActive());

            addressToOrderDtoList.add(addressToOrderDto);
        }
        List<PostOfficeUser> postOfficeUserList = new ArrayList<>(myUser.getPostOfficeUsers());

        List<PostOfficeUserDto> postOfficeToOrderDtoList = new ArrayList<>();

        for(PostOfficeUser postOfficeUser:postOfficeUserList){

            PostOfficeUserDto postOfficeToOrderDto = new PostOfficeUserDto(
                    postOfficeUser.getId(),
                    postOfficeUser.getPostOffice(),
                    postOfficeUser.isActive() );

            postOfficeToOrderDtoList.add(postOfficeToOrderDto);
        }
        UserDto userDto=new UserDto(
                myUser.getId(),
                myUser.getName(),
                myUser.getUsername(),
                myUser.getEmail(),
                myUser.isActive(),
                PAGE_NUM,
                phoneUserToOrderDtoList,
                addressToOrderDtoList,
                postOfficeToOrderDtoList
        );
        return userDto;
    }


    public List<UserDto> searchUser(String us){

        return getJsonMyUser(myUserRepository.findByNameAndUsername(us));
    }


    public List<UserDto> listUserDto(){

        List<UserDto> userList=myUserRepository.listUserDto();

        return userList;
    }
    public List<UserDto> listUserLimit(Pageable pageable){

        List<UserDto> userList=myUserRepository.listUserLimit(pageable);

        return userList;
    }


    public  Map<String,String> updateMyUser(MyUser user,
                                            String username,
                                            String name,
                                            String email,
                                            int rating,
                                            String info,
                                            boolean active
    ) {
        Map<String,String> errorMessage= new HashMap<>();

        if(!user.getUsername().equals(username)) {

            if (myUserRepository.findByUsername(username) != null) {

                errorMessage.put("usernameError", "Такий логін вже існує");
            }
        }
        if (email.length()>3&&!user.getEmail().equals(email)) {

            if (myUserRepository.findByEmail(email) != null) {

                errorMessage.put("emailError", "Такий email вже існує");
            }
        }
        if(errorMessage.size()==0){

            user.setUsername(username);
            user.setEmail(email);
            user.setName(name);
            user.setRating(rating);
            user.setInfo(info);
            user.setActive(active);

            myUserRepository.save(user);
        }
        return errorMessage;
    }


    MyUser saveUserBasket(MyUser myUser){

        return myUserRepository.save(myUser);
    }


    public MyUser searchEmail(String email) {

        return myUserRepository.findByEmail(email);
    }


    public List<MyUser> partMyUserList() {

        return  myUserRepository.findAll().subList(START_LIST,Constants.LIST_SIZE);
    }


    public List<UserDto> scrollMyUserList(int start) {

        PAGE_NUM=start;

        SIZE_USER_PAGE=(myUserRepository.countUser()/Constants.LIST_SIZE);

        if(SIZE_USER_LIST==0){

            SIZE_USER_LIST = myUserRepository.countUser();
        }
        List<MyUser> userList = new ArrayList<>();

         if(PAGE_NUM==0){

            userList = myUserRepository.findAll().subList(PAGE_NUM,PAGE_NUM+Constants.LIST_SIZE);

            return getJsonMyUser(userList);
        }else if((PAGE_NUM+Constants.LIST_SIZE)<SIZE_USER_LIST){

            userList = myUserRepository.findAll().subList(PAGE_NUM,PAGE_NUM+Constants.LIST_SIZE);

            return getJsonMyUser(userList);
        }else if(SIZE_USER_LIST-PAGE_NUM<Constants.LIST_SIZE){

            userList = myUserRepository.findAll().subList(PAGE_NUM,SIZE_USER_LIST);

            PAGE_NUM=0;

            return getJsonMyUser(userList);
        }else{
             PAGE_NUM=0;
            return getJsonMyUser(myUserRepository.findAll());
        }

    }


    private List<UserDto> getJsonMyUser(List<MyUser> userList){

        List<UserDto>userDtos= new ArrayList<>();

        for(MyUser user:userList){

          List<PhoneUserDto> phoneDtos=  phoneService.getListPhoneDtoByListPhone(user.getPhones());

          List<AddressUserDto> addressDtos=  addressService.getListAddressDtoByListAddress(user.getAddresses());

          List<PostOfficeUserDto> postOfficeDtos=  postOfficeService.getListPostOfficeDtoByListPostOffice(user.getPostOfficeUsers());

          UserDto userDto = new UserDto(user.getId(),
                    user.getName(),
                    user.getUsername(),
                    user.getEmail(),
                    user.isActive(),
                    PAGE_NUM,
                    phoneDtos,
                    addressDtos,
                    postOfficeDtos);

            userDtos.add(userDto);
        }
        return userDtos;
    }

}
//==============UserService
//SIZE_PAGE=myUserRepository.countUser()/Constants.LIST_SIZE;

//PAGE_NUM +=Constants.LIST_SIZE;//PAGE_NUM=SIZE_PAGE-PAGE_NUM; //PAGE_NUM=Constants.LIST_SIZE;


//findByUsernameAndName
//        List<UserDto> userList=new ArrayList<>();
//              for(UserDto usr:myUserRepository.searchUserNickName(us)){
//                   usr.setPageNum(0);
//                   userList.add(usr);
//              }
//        if(SIZE_LIST==0){
//            SIZE_LIST=myUserRepository.countUser();
//        }
//        List<MyUser> userList = new ArrayList<>();
//
//        double res=SIZE_LIST/Constants.LIST_SIZE;
//
//        System.out.println(SIZE_LIST);
//        System.out.println(START_LIST);
//        System.out.println(res);
//
//        //scrollMyUserList(END_LIST++);
//
//        if(START_LIST==0){
//
//            userList = myUserRepository.findAll().subList(START_LIST,START_LIST+Constants.LIST_SIZE);
//
//            START_LIST=Constants.LIST_SIZE;
//
//            return userList;
//        }else if((START_LIST+Constants.LIST_SIZE)<SIZE_LIST){
//
//            userList = myUserRepository.findAll().subList(START_LIST,START_LIST+Constants.LIST_SIZE);
//
//            START_LIST +=Constants.LIST_SIZE;
//
//            return userList;
//        }else if(SIZE_LIST-START_LIST<Constants.LIST_SIZE){
//
//            userList = myUserRepository.findAll().subList(START_LIST,SIZE_LIST);
//
//            START_LIST=0;
//
//            return userList;
//        }else{
//
//            START_LIST=0;
//
//            return myUserRepository.findAll();
//        }
