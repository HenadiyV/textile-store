package com.vognev.textilewebproject.service;


import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.dto.AddressUserDto;
import com.vognev.textilewebproject.model.dto.PhoneUserDto;
import com.vognev.textilewebproject.model.dto.PostOfficeUserDto;
import com.vognev.textilewebproject.model.dto.UserDto;
import com.vognev.textilewebproject.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

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

        //sendUser(save(myUser));//может мешать антивирус и быть заблокированны поля  провайдером
        return true;
    }


    public MyUser save(MyUser user){
        return myUserRepository.save(user);
    }

    //UserDto
    public Map<String,String> addUserFromAdmin(
            MyUser userDto,
            String info,
            String phone,
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

            addressService.saveUserAddress(city,address,postCode,newUser);

            postOfficeService.saveUserPostOffice(postOffice,newUser);

        }
        //sendUser(myUser);//может мешать антивирус и быть заблокированны поля  провайдером
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


    public void updateProfile(MyUser user,
                              String password,
                              String email
    ) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null&& !email.equals(userEmail))||
                (userEmail != null && !userEmail.equals(email));

        if(isEmailChanged){

            user.setEmail(email);

            if(!ObjectUtils.isEmpty((email))){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if(!ObjectUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
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

            PhoneUserDto phoneUserToOrderDto =new PhoneUserDto(phoneUser.getId(),phoneUser.getPhone());

            phoneUserToOrderDtoList.add(phoneUserToOrderDto);
        }

        List<AddressUser>addressUserList=new ArrayList<>(myUser.getAddresses());

        List<AddressUserDto>addressToOrderDtoList = new ArrayList<>();

        for(AddressUser addressUser:addressUserList){

            AddressUserDto addressToOrderDto= new AddressUserDto(
                    addressUser.getId(),
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
                    postOfficeUser.getPostOffice());

            postOfficeToOrderDtoList.add(postOfficeToOrderDto);
        }
        UserDto userDto=new UserDto(
                myUser.getId(),
                myUser.getName(),
                myUser.getUsername(),
                myUser.getEmail(),
                myUser.isActive(),
                phoneUserToOrderDtoList,
                addressToOrderDtoList,
                postOfficeToOrderDtoList
        );
        return userDto;
    }


    public List<UserDto> searchUser(String us){

        List<UserDto> userList=myUserRepository.searchUserNickName(us);

        return userList;
    }


    public List<UserDto> listUserDto(){

        List<UserDto> userList=myUserRepository.listUserDto();

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
}
