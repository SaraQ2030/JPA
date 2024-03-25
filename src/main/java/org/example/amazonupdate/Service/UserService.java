package org.example.amazonupdate.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.Model.Merchant;
import org.example.amazonupdate.Model.MerchantStock;
import org.example.amazonupdate.Model.Product;
import org.example.amazonupdate.Model.User;
import org.example.amazonupdate.Repository.MerchantRepository;
import org.example.amazonupdate.Repository.MerchantStockRepository;
import org.example.amazonupdate.Repository.ProductRepository;
import org.example.amazonupdate.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
   private final ProductRepository productRepository;
   private final MerchantRepository merchantRepository;
  private final MerchantStockRepository merchantStockRepository;

  public List<User> getAllUser(){
      return userRepository.findAll();
  }

    public void addUser(User user){
      userRepository.save(user);
    }
      public Boolean updateUser( Integer id,User user){
        User c=userRepository.getById(id);

        if (c==null){
            return false;
        }
        c.setUserName(user.getUserName());
        c.setBalance(user.getBalance());
        c.setEmail(user.getEmail());
        c.setPassword(user.getPassword());
        c.setRole(user.getRole());

      userRepository.save(c);
        return true;
    }


    public Boolean deleteUser(Integer id){
        User u=userRepository.getById(id);
        if (userRepository.existsById(id)) {
            userRepository.delete(u);
            return true;
        }

        return false;

    }


    //admin delete from merchant - users -product by id
    public boolean deleteAdmin(int id,int del,int id_del){
//        for (User u:users){
            User u=userRepository.getReferenceById(id);
            if (u.getId().equals(id) && u.getRole().equalsIgnoreCase("admin")){
                switch (del)
                {
                    case 1:
                        for (Merchant m:merchantRepository.findAll()){
                            if (m.getId()==id_del){
                                merchantRepository.delete(m);
                                return true;
                            } break;}
                    case 2:
                        for (Product p: productRepository.findAll()){
                            if (p.getId().equals(id_del)){
                                productRepository.delete(p);
                                return true;
                            }  break;}
                    case 3:
                        for (User user:userRepository.findAll()){
                            if (user.getId()==id_del && user.getRole().equalsIgnoreCase("customer")){
                                userRepository.delete(user);
                                return true;
                            }  break;}
                }
            }


        return false;
    }



    //list of products  user want to buy by id
    public List<Product> addToCart(int userId, int merchantId, int productId,int num) {
        List<Product> list=new ArrayList<>();
        while (num!=0) {
            for (User u : userRepository.findAll()) {
                for (Merchant m : merchantRepository.findAll()) {
                    for (Product p : productRepository.findAll()) {
                        for (MerchantStock ms : merchantStockRepository.findAll()) {
                            if (u.getId().equals(userId) && m.getId().equals(merchantId) && p.getId().equals(productId)
                                    && u.getBalance() >= p.getPrice() && ms.getStock() > 0) {
                                if (ms.getProduct_id().equals(p.getId()) && ms.getMerchant_id().equals(m.getId())) {
                                    list.add(p);
                                    num--;
                                    ms.setStock(ms.getStock() - 1);
                                    u.setBalance(u.getBalance() - p.getPrice());


                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }


    // 12- user buy a product directly
//user use discount coupone 15
    public boolean buyProduct(int userId, int merchantId, int productId,int dis) {
        int discount=1;
        for (User u : userRepository.findAll()) {
            for (Merchant m : merchantRepository.findAll()) {
                for (Product p : productRepository.findAll()) {
                    for (MerchantStock ms: merchantStockRepository.findAll()){
                        if (u.getId().equals(userId) && m.getId().equals(merchantId) && p.getId().equals(productId)
                                && u.getBalance() >= p.getPrice() &&  ms.getStock()>0) {
                            if (ms.getProduct_id().equals(p.getId()) && ms.getMerchant_id().equals(m.getId()) ){
                                //use discount
                                if (dis==15){
                                    ms.setStock(ms.getStock()-1);
                                    u.setBalance(u.getBalance()- (discount(p.getPrice(),0.15)) );
                                    return true;
                                }
                                else{
                                    ms.setStock(ms.getStock()-1);
                                    u.setBalance(u.getBalance()-p.getPrice());
                                    return true;
                                }


                            }}}}}}
        return false;
    }


    // discount method on product
    public double discount(double price ,double dis){
        return  price*dis;
    }


}
