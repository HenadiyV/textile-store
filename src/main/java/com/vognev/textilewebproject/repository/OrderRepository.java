package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * textilewebproject_2  16/10/2021-9:37
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

    Page<Order> findAll(Pageable pageable);

    @Query("from Order ord where ord.phoneUser.phone like %:phone")
    Page<Order> findByPhoneUser_Phone(@Param("phone")String phone, Pageable pageable);//

    @Query("from Order ord where ord.user.name like :name%")
    Page<Order> findByUser_Name(@Param("name")String name, Pageable pageable);

    @Query("from Order ord where ord.user.username like :username%")
    Page<Order> findByUser_Username(@Param("username")String username, Pageable pageable);

    Page<Order> findAllByCreat(@Param("dat")Date dat, Pageable pageable);

    Page<Order> findAllByDispatch(@Param("dat")Date dat, Pageable pageable);

    @Query("from Order ord where ord.delivery like :ttn%")
    Page<Order> findByDelivery(@Param("ttn")String ttn,Pageable pageable);

    @Query("from Order ord where ord.status =:status")
    Page<Order> findByStatus(@Param("status")String status,Pageable pageable);

    List<Order>findAllByUser_Id(Long user_id);

    @Query("select count(ord) from Order ord")
    Integer getCountOdrers();

//    @Query("select new com.vognev.textilewebproject.dto.OrderDto" +
//            "(ord.id,ord.dispatch,ord.status,ord.delivery,ord.info,ord.create," +
//            "select SUM(crt.siz*crt.sellingPrice) new com.vognev.textilewebproject.dto.CartDto(crt.id)from Cart crt where crt.order_id=ord.id"+
//    "ord.phoneUser.id,ord.addressUser.id,)from Order ord order by ord.id desc")
    /*Long id,
                    Date dat_dispatch,
                    String status,
                    String delivery,
                    String info_order,
                    Date dat_create,
                    List<CartDto> carts,
                    Double summ,
                    Long user_id,
                    String username,
                    String name,
                    Long phone_id,
                    String phone,
                    Long address_id,
                    String region,
                    String district,
                    String city,
                    String address,

                    String postCode,
                    Long postOffice_id,
                    String postOffice*/
}
