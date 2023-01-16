package com.example.rentBuddy.Model.User;

import com.example.rentbuddy.PersistenceLayer.IUserPersistence;
import com.example.rentbuddy.Model.EncoderDecoder.EncoderDecoderFactory;
import com.example.rentbuddy.Model.EncoderDecoder.IEncoderDecoder;
import com.example.rentbuddy.Model.User.IUser;
import com.example.rentbuddy.Model.User.User;
import com.example.rentbuddy.Model.User.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
public class UserTest {
    @Test
    public void loadValidUserTest()
    {

        IUser user = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();
        IUser userDetails = user.load("vaishwi@gmail.com", userPersistence);

        Assertions.assertEquals("Landlord",((User) userDetails).getUserType());
        Assertions.assertEquals("314242", ((User) userDetails).getPassword());
    }

    @Test
    public void loadUserStorageFailureTest(){

        IUser u = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();
        IUser userDetails = u.load("@gmail.com", userPersistence);

        Assertions.assertEquals(null, userDetails);
    }

    @Test
    public void saveUserTest()
    {
        IUser user = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();
        String userId = "vaishwi@gmail.com";
        String password = "5362327";
        String firstName = "Vaishwi";
        String lastName  = "Patel";
        String dateofBirth = "01-10-1999";
        String contactNumber = "9029895768";
        String userType = "Landlord";

        try
        {
            IUserPersistence.StorageResult result = user.save(userId, password, firstName, lastName, dateofBirth, contactNumber, userType,userPersistence);
            Assertions.assertEquals(IUserPersistence.StorageResult.SUCCESS, result);

        }
        catch (SQLException e)
        {
            Assertions.fail("Save failed, threw exception:" + e.getMessage());
        }
    }

    @Test
    public void updatePasswordTest()
    {
        IUser user = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();

        IUserPersistence.StorageResult result = user.updatePassword(userPersistence);
        Assertions.assertEquals(IUserPersistence.StorageResult.SUCCESS, result);
    }

    @Test
    public void loginValidUser()
    {
        IUser user = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();

        IUser.UserLoginResult result= user.loginUser("vaishwi@gmail.com","314242",userPersistence);
        Assertions.assertEquals(IUser.UserLoginResult.VALID_USER,result);
        Assertions.assertEquals("Landlord",((User)user).getUserType());
    }

    @Test
    public void loginInvalidUser()
    {
        IUser user = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();

        IUser.UserLoginResult result= user.loginUser("vaishwi@gmail.com","31",userPersistence);
        Assertions.assertEquals(IUser.UserLoginResult.INVALID_USER,result);
    }

    @Test
    public void loginNotExistUser()
    {
        IUser user = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();

        IUser.UserLoginResult result= user.loginUser("hetvi@gmail.com","31",userPersistence);
        Assertions.assertEquals(IUser.UserLoginResult.USER_DOES_NOT_EXIST,result);
    }

    @Test
    public void registerUser()
    {
        IUser user = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();
        String userId = "hitesh@gmail.com";
        String password = "5362327";
        String firstName = "Hitesh";
        String lastName  = "Patel";
        String dateofBirth = "01-10-1999";
        String contactNumber = "9029895768";
        String userType = "Landlord";

        IUser.UserRegisterResult result = user.register(userId, password, firstName, lastName, dateofBirth, contactNumber, userType,userPersistence);

        Assertions.assertEquals(IUser.UserRegisterResult.SUCCESS,result);
    }

    @Test
    public void registerExistingUser()
    {
        IUser user = UserFactory.instance().createUser();
        IUserPersistence userPersistence = UserPersistenceMockFactory.instance().createUserPersistence();
        String userId = "vaishwi@gmail.com";
        String password = "5362327";
        String firstName = "Vaishwi";
        String lastName  = "Patel";
        String dateofBirth = "01-10-1999";
        String contactNumber = "9029895768";
        String userType = "Landlord";

        IUser.UserRegisterResult result = user.register(userId, password, firstName, lastName, dateofBirth, contactNumber, userType,userPersistence);

        Assertions.assertEquals(IUser.UserRegisterResult.USER_EXIST,result);
    }

    @Test
    public void generateResetURLTest()
    {
        IUser user = UserFactory.instance().createUser();
        String pageURL ="http://localhost:8080/";
        String userId = "vaishwipatel82110@gmail.com";
        IEncoderDecoder encoderDecoder = EncoderDecoderFactory.instance().createEncoderDecoder();

        String result = user.generateResetURL(pageURL, userId, encoderDecoder);

        Assertions.assertEquals("http://localhost:8080/resetPassword/dmFpc2h3aXBhdGVsODIxMTBAZ21haWwuY29t",result);
    }

    @Test
    public void invalidgenerateResetURLTest()
    {
        IUser user = UserFactory.instance().createUser();
        String pageURL ="http://localhost:8080/";
        String userId = null;
        IEncoderDecoder encoderDecoder = EncoderDecoderFactory.instance().createEncoderDecoder();

        String result = user.generateResetURL(pageURL, userId, encoderDecoder);

        Assertions.assertEquals(null,result);
    }
}
