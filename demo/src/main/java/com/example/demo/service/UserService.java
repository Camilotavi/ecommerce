package com.example.demo.service;

import com.example.demo.DTO.updateUserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUser()
    {
        return (List<User>) this.userRepository.findAll();
    }

    public User getUser(int id)
    {
        Optional<User> optional = this.userRepository.findById(id);
        return optional.get();
    }
    public User getUserByEmail(String email)
    {
        return this.userRepository.findUserByemail(email);
    }

    public int getIdByEmail(String email){
        User user = this.userRepository.findUserByemail(email);
        return user.getId();
    }

    public void updateUser(String email, updateUserDTO newUser)
    {
        User currentUser=this.getUserByEmail(email);

        currentUser.setName(newUser.getName());
        currentUser.setPhoto_url(newUser.getPhoto_url());
        this.userRepository.save(currentUser);
    }

    public void deleteUser(int id)
    {
        this.userRepository.deleteById(id);
    }

    public void addUser(User user)
    {

        String Hashed_password = passwordEncoder.encode(user.getPassword_hash());
        user.setPassword_hash(Hashed_password);
        this.userRepository.save(user);
    }

    public boolean validateLoginCredentials(String email,String password)
    {
        User user = userRepository.findUserByemail(email);
        if (user == null){
            return false;
        }else{
            return passwordEncoder.matches(password, user.getPassword_hash());
        }
    }

    public User approveUser(int id){
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        user.setStatus(User.Status.approved);

        return userRepository.save(user);
    }



}
