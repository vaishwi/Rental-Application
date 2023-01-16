package com.example.rentbuddy.Model.User;

import com.example.rentbuddy.PersistenceLayer.IUserPersistence;
import com.example.rentbuddy.Model.EncoderDecoder.IEncoderDecoder;

import java.sql.SQLException;

public class User implements IUser {

    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String contactNumber;
    private String userType;

    private IUserPersistence userPersistence;

    public User()
    {
    }
    public User(String password, String userType)
    {
        this.password = password;
        this.userType = userType;
    }

    public User(String userId) {
        this.userId = userId;
    }


    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() { return userType; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public IUser load(String userId, IUserPersistence userPersistence)
    {
        try {
            return userPersistence.loadUser(userId);

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public IUserPersistence.StorageResult save(String userId, String password, String firstName, String lastName, String dateofBirth, String contactNumber, String userType, IUserPersistence p) throws SQLException
    {
        return p.saveUser(userId, password, firstName, lastName, dateofBirth, contactNumber, userType);
    }

    @Override
    public IUserPersistence.StorageResult updatePassword(IUserPersistence userPersistence)
    {
        try
        {
            IUserPersistence.StorageResult result = userPersistence.updatePassword(this.userId,this.password);
            return result;
        }
        catch (SQLException e)
        {
            return IUserPersistence.StorageResult.STORAGE_FAILURE;
        }
    }

    @Override
    public IUser.UserLoginResult loginUser(String userId,String password,IUserPersistence userPersistence)
    {
        String enteredPassword = password;
        User user = (User)this.load(userId, userPersistence);

        if(user == null)
        {
            return IUser.UserLoginResult.USER_DOES_NOT_EXIST;
        }

        if(enteredPassword.equals(user.getPassword()))
        {
            this.userType = user.getUserType();

            return IUser.UserLoginResult.VALID_USER;
        }
        else
        {
            return IUser.UserLoginResult.INVALID_USER;
        }
    }

    @Override
    public IUser.UserRegisterResult register(String userId, String password, String firstName, String lastName,String dateofBirth, String contactNumber,String userType,IUserPersistence userPersistence)
    {
        try
        {
            IUser user = (User)this.load(userId,userPersistence);

            if(user == null)
            {
                this.save(userId, password, firstName, lastName, dateofBirth, contactNumber, userType, userPersistence);
                return IUser.UserRegisterResult.SUCCESS;
            }
            return UserRegisterResult.USER_EXIST;
        }
        catch (SQLException e)
        {
            return IUser.UserRegisterResult.STORAGE_FAILURE;
        }
    }

    @Override
    public String generateResetURL(String pageURL, String userId, IEncoderDecoder encoderDecoder)
    {
        if(pageURL == null || userId == null)
        {
            return null;
        }
        String baseURL = getBaseURL(pageURL);
        String resetURL = baseURL +"resetPassword/" + encoderDecoder.encode(userId);

        return resetURL;
    }

    private String getBaseURL(String URL) {
        String baseURL = "";
        baseURL = URL.split("/")[0] + "//" + URL.split("/")[2] + "/";
        return baseURL;
    }

}
