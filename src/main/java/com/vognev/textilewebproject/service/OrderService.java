package com.vognev.textilewebproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.dto.*;
import com.vognev.textilewebproject.util.Constants;
import com.vognev.textilewebproject.util.OrderSumm;
import com.vognev.textilewebproject.repository.OrderDeletedRepository;
import com.vognev.textilewebproject.repository.OrderRepository;
import com.vognev.textilewebproject.util.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * textilewebproject_2  16/10/2021-20:05
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PostOfficeService prostOfficeService;

    @Autowired
    private CartService cartService;


    @Autowired
    private OrderDeletedRepository orderDeletedRepository;


    public List<OrderDto> orderAll() {

        Iterable<Order> orders = orderRepository.findAll();

        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {

            double summ=0;

            List<CartDto> cartDtos = cartService.getCartDtoListFromOrder(order.getId(),order.getCarts());

            orderDtos.add(
                    new OrderDto(
                            order.getId(),
                            order.getDispatch(),
                            order.getStatus(),
                            order.getDelivery(),
                            order.getInfo(),
                            order.getCreat(),
                            cartDtos,
                            OrderSumm.getOrderSumm(),
                            order.getUser().getId(),
                            order.getUser().getUsername(),
                            order.getUser().getName(),
                            order.getPhoneUser().getId(),
                            order.getPhoneUser().getPhone(),
                            order.getAddressUser().getId(),
                            order.getAddressUser().getRegion(),
                            order.getAddressUser().getDistrict(),
                            order.getAddressUser().getCity(),
                            order.getAddressUser().getAddress(),
                            order.getAddressUser().getPostCode(),
                            order.getPostOfficeUser().getId(),
                            order.getPostOfficeUser().getPostOffice())
            );
            OrderSumm.setOrderSumm(0);
        }
        return orderDtos;
    }


    public List<OrderDto> orderAllToUser(Long userId) {

        Iterable<Order> orders = orderRepository.findAllByUser_Id(userId);

        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {

            double summ=0;

            List<CartDto> cartDtos = cartService.getCartDtoListFromOrder(order.getId(),order.getCarts());

            orderDtos.add(
                    new OrderDto(
                            order.getId(),
                            order.getDispatch(),
                            order.getStatus(),
                            order.getDelivery(),
                            order.getInfo(),
                            order.getCreat(),
                            cartDtos,
                            OrderSumm.getOrderSumm(),
                            order.getUser().getId(),
                            order.getUser().getUsername(),
                            order.getUser().getName(),
                            order.getPhoneUser().getId(),
                            order.getPhoneUser().getPhone(),
                            order.getAddressUser().getId(),
                            order.getAddressUser().getRegion(),
                            order.getAddressUser().getDistrict(),
                            order.getAddressUser().getCity(),
                            order.getAddressUser().getAddress(),

                            order.getAddressUser().getPostCode(),
                            order.getPostOfficeUser().getId(),
                            order.getPostOfficeUser().getPostOffice())
            );
            OrderSumm.setOrderSumm(0);
        }
        return orderDtos;
    }


    public Order createOrder(Date dat_dispatch,
                             String status,
                             String delivery,
                             String info,
                             Long userId,
                             Long addressId,
                             Long phoneId,
                             Long postOfficeId
    ){
        try{
            Date datCreate= new Date();

            Order order = new Order(null,
                    dat_dispatch,
                    status,
                    delivery,
                    info,
                    datCreate,
                    userService.getUser(userId),
                    addressService.getAddressById(addressId),
                    phoneService.getPhoneById(phoneId),
                    prostOfficeService.getPostOfficeById(postOfficeId));

            return save(order);

        }catch(Exception ex){
            System.out.println("createOrderErr");
            ex.printStackTrace();
        }
        return null;
    }


    public Order save(Order order) {

      return  orderRepository.save(order);
    }


    public void updateOrder(Order order,
                            Long user_id,
                            Long phone_id,
                            Long address_id,
                            Long post_office_id,
                            String dat_dispatch,
                            String status,
                            String delivery,
                            String info_order
    ){
        Date dat=DateHelper.convertStringToDate(dat_dispatch);

        order.setUser(userService.getUser(user_id));
        order.setAddressUser(addressService.getAddressById(address_id));
        order.setDispatch(dat);
        order.setDelivery(delivery);
        order.setPhoneUser(phoneService.getPhoneById(phone_id));
        order.setPostOfficeUser(prostOfficeService.getPostOfficeById(post_office_id));
        order.setInfo(info_order);
        order.setStatus(status);

        orderRepository.save(order);
    }


    Order getOrderById(Long id){
        return getById(id);
    }


    public OrderDto getOrderByIdFromOrderDto(Long id){

        Order order = getById(id);

        if(order!=null){
            List<CartDto> cartDtos = cartService.getCartDtoListFromOrder(order.getId(),order.getCarts());

            OrderDto orderDto=new OrderDto(
                    order.getId(),
                    order.getDispatch(),
                    order.getStatus(),
                    order.getDelivery(),
                    order.getInfo(),
                    order.getCreat(),
                    cartDtos,
                    OrderSumm.getOrderSumm(),
                    order.getUser().getId(),
                    order.getUser().getUsername(),
                    order.getUser().getName(),
                    order.getPhoneUser().getId(),
                    order.getPhoneUser().getPhone(),
                    order.getAddressUser().getId(),
                    order.getAddressUser().getRegion(),
                    order.getAddressUser().getDistrict(),
                    order.getAddressUser().getCity(),
                    order.getAddressUser().getAddress(),

                    order.getAddressUser().getPostCode(),
                    order.getPostOfficeUser().getId(),
                    order.getPostOfficeUser().getPostOffice());

            OrderSumm.setOrderSumm(0);

            return orderDto;
        }
        return null;
    }

    private Order getById(Long id) {
        Optional<Order> orderOpt=orderRepository.findById(id);
        return orderOpt.orElse(null);
    }


    public  void addCartToOrder(List<String>list) throws JsonProcessingException {

        Date dt= new Date();

        for(String s: list){

            AddCartToDto ad=new ObjectMapper().readValue(s,AddCartToDto.class);

            Order ord= getOrderById(ad.getOrderId());

            Cart cr=new Cart();
            cr.setProduct_id(ad.getProductId());
            cr.setSalePrice(ad.getSellingPrice());
            cr.setNameProduct(ad.getProductName());
            cr.setDiscount_price(ad.getBonus());
            cr.setSiz(ad.getMetr());

            if(ad.getInfo()==null){
                cr.setInfo("Info");
            }else{
                cr.setInfo(ad.getInfo());
            }

            cr.setDat(dt);
            cr.setOrder(ord);

            cartService.save(cr);
        }
    }

    // смена пользователя в заказе
    public void updateOrderByUser(OrderDto orderDto) {

        MyUser user =userService.getUser(orderDto.getUser_id());

        AddressUser addressUser = addressService.getAddressById(orderDto.getAddress_id());

        PhoneUser phoneUser = phoneService.getPhoneById(orderDto.getPhone_id());

        PostOfficeUser postOfficeUser = prostOfficeService.getPostOfficeById(orderDto.getPostOffice_id());

        orderRepository.save(new Order(
                orderDto.getId(),
                orderDto.getDat_dispatch(),
                orderDto.getStatus(),
                orderDto.getDelivery(),
                orderDto.getInfo_order(),
                orderDto.getDat_create(),
                user,
                addressUser,
                phoneUser,
                postOfficeUser));

    }

    // удаление заказа
    public void deleteOrder(Long id){

        Order order = getById(id);

        if(order!=null){


        Date dat_delete= new Date();

        java.sql.Date sqlDate = new java.sql.Date( dat_delete.getTime() );

        String address=order.getAddressUser().getCity()+" | "+order.getAddressUser().getAddress()+" | "+
                order.getAddressUser().getPostCode();

        Double summ= cartService.summOrder(order.getCarts());

        String info=order.getInfo();
        if(info==null){
            info=" ";
        }
        OrderDeleted orderDeleted =new OrderDeleted(null,
                order.getId(),
                order.getUser().getId(),
                order.getUser().getUsername(),
                order.getUser().getName(),
                order.getPhoneUser().getPhone(),
                address,
                order.getPostOfficeUser().getPostOffice(),
                summ,
                order.getCreat(),
                dat_delete,
                order.getStatus(),
                info,
                false);

        cartService.deleteListCart(order.getCarts());

        orderDeletedRepository.save(orderDeleted);

        orderRepository.delete(order);
        }
    }


    Order saveOrderBasket(MyUser myUser,AddressUser addressUser,PhoneUser phoneUser,PostOfficeUser postOfficeUser, String info){
        try{
            Date today=new Date();
            long ltime=today.getTime()+3*Constants.ONE_DAY;
            Date today3=new Date(ltime);

            Order order = new Order(null,
                    today3,
                    "не оплачений",
                    " ",
                    info,
                    today,
                    myUser,
                    addressUser,
                    phoneUser,
                    postOfficeUser);
            return orderRepository.save(order);

        }catch(Exception ex){
            System.out.println("Error-SaveOrderBasket 355");
            ex.printStackTrace();
            return null;
        }
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

     AddressUser getAdressUser(MyUser user){

         for(AddressUser addressUser : user.getAddresses()){

            if(addressUser!=null){

                return addressUser;
            }
         }
         return null;
     }


     PhoneUser getPhoneUser(MyUser user){

         for(PhoneUser phone : user.getPhones()){

            if(phone!=null){

                return phone;
            }
         }
         return null;
     }


     PostOfficeUser getPostOfficeUser(MyUser user){

         for(PostOfficeUser postOffice : user.getPostOfficeUsers()){

            if(postOffice!=null){

                return postOffice;
            }
         }
         return null;
     }


    List<Order> getListOrderByUserId(long userId) {

        return orderRepository.findAllByUser_Id(userId);
    }


    public Page<OrderDto> getOrderListAllPageable(Pageable pageable) {

        Page<Order>pageOrder=orderRepository.findAll(pageable);

        int totalElements=(int)pageOrder.getTotalElements();

        List<OrderDto> orderDtos=convertListOrderToListOrderDto(pageOrder.getContent());

       Page<OrderDto> page = new PageImpl<>(orderDtos,pageable,totalElements);

        return page;//toPage(orderDtos,pageable)orderDtos.size()
    }


    private List<OrderDto> convertListOrderToListOrderDto(List<Order> list){

        List<OrderDto> orderDtos = new ArrayList<OrderDto>();

        for(Order ord:list){

            List<CartDto> cartDtos = cartService.getCartDtoListFromOrder(ord.getId(),ord.getCarts());

            OrderDto orderDto = new OrderDto(ord.getId(),
                    ord.getDispatch(),ord.getStatus(),ord.getDelivery(),
                    ord.getInfo(),ord.getCreat(),
                    cartDtos, OrderSumm.getOrderSumm(),ord.getUser().getId(),
                    ord.getUser().getUsername(),ord.getUser().getName(),
                    ord.getPhoneUser().getId(),ord.getPhoneUser().getPhone(),
                    ord.getAddressUser().getId(),ord.getAddressUser().getRegion(),
                    ord.getAddressUser().getDistrict(),
                    ord.getAddressUser().getCity(),ord.getAddressUser().getAddress(),
                    ord.getAddressUser().getPostCode(),
                    ord.getPostOfficeUser().getId(),ord.getPostOfficeUser().getPostOffice());

            orderDtos.add(orderDto);
        }
        return orderDtos;
    }


    public Page<Order> getPageOrders(Pageable pageable){

        return orderRepository.findAll(pageable);
     }


    private Page<OrderDto> toPage(List<OrderDto> list, Pageable pageable) {

        int start = (int) pageable.getOffset();

        int end = Math.min((start + pageable.getPageSize()), list.size());
//        System.out.println("start"+start);
//        System.out.println("end"+end);
//        System.out.println("pageable.getPageSize()"+pageable.getPageSize());
        if(start > list.size())

            return new PageImpl<>(new ArrayList<>(), pageable, list.size());

        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }


    public Page<OrderDto> searchOrder(String point,String val,Pageable pageable){

        switch (point){
            case "2": return searchName(val,pageable);

            case "1" :return searchUsername(val,pageable);

            case "3" :return searchCreate(val,pageable);

            case "4" :return searchDispatch(val,pageable);

            case "5" :return searchUser_Phone(val,pageable);

            case "6" :return searchDelivery(val,pageable);

            case "7" : return searchStatus(val,pageable);

            default: return null;
        }
    }


    private Page<OrderDto> searchName(String val, Pageable pageable){

        Page<Order> pageOrder = orderRepository.findByUser_Name(val,pageable);

        int totalElements=(int)pageOrder.getTotalElements();

        List<OrderDto> orderDtos=convertListOrderToListOrderDto(pageOrder.getContent());

        return new PageImpl<>(orderDtos,pageable,totalElements);

    }


    private Page<OrderDto> searchUsername(String val, Pageable pageable){

        Page<Order> pageOrder = orderRepository.findByUser_Username(val,pageable);

        int totalElements=(int)pageOrder.getTotalElements();

        List<OrderDto> orderDtos=convertListOrderToListOrderDto(pageOrder.getContent());

        return  new PageImpl<>(orderDtos,pageable,totalElements);
    }


    private Page<OrderDto> searchCreate(String val, Pageable pageable){

        Date dat= DateHelper.convertStringToDateFromSearch(val);

        Page<Order> pageOrder = orderRepository.findAllByCreat(dat,pageable);

        int totalElements=(int)pageOrder.getTotalElements();

        List<OrderDto> orderDtos=convertListOrderToListOrderDto(pageOrder.getContent());

        return  new PageImpl<>(orderDtos,pageable,totalElements);
    }


    private Page<OrderDto> searchDispatch(String val, Pageable pageable){

        Date dat= DateHelper.convertStringToDateFromSearch(val);

        Page<Order> pageOrder = orderRepository.findAllByDispatch(dat,pageable);

        int totalElements=(int)pageOrder.getTotalElements();

        List<OrderDto> orderDtos=convertListOrderToListOrderDto(pageOrder.getContent());

        return  new PageImpl<>(orderDtos,pageable,totalElements);
    }


    private Page<OrderDto> searchUser_Phone(String val, Pageable pageable){

        Page<Order> pageOrder = orderRepository.findByPhoneUser_Phone(val,pageable);

        int totalElements=(int)pageOrder.getTotalElements();

        List<OrderDto> orderDtos=convertListOrderToListOrderDto(pageOrder.getContent());

        return  new PageImpl<>(orderDtos,pageable,totalElements);
    }


    private Page<OrderDto> searchDelivery(String val, Pageable pageable){

        Page<Order> pageOrder = orderRepository.findByDelivery(val,pageable);

        int totalElements=(int)pageOrder.getTotalElements();

        List<OrderDto> orderDtos=convertListOrderToListOrderDto(pageOrder.getContent());

        return  new PageImpl<>(orderDtos,pageable,totalElements);
    }


    private Page<OrderDto> searchStatus(String val, Pageable pageable){

        Page<Order> pageOrder = orderRepository.findByStatus(val,pageable);

        int totalElements=(int)pageOrder.getTotalElements();

        List<OrderDto> orderDtos=convertListOrderToListOrderDto(pageOrder.getContent());

        return  new PageImpl<>(orderDtos,pageable,totalElements);
    }
}