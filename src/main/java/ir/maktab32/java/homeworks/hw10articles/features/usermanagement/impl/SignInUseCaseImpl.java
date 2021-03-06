package ir.maktab32.java.homeworks.hw10articles.features.usermanagement.impl;

import ir.maktab32.java.homeworks.hw10articles.entities.db2.User;
import ir.maktab32.java.homeworks.hw10articles.features.usermanagement.usecase.SignInUseCase;
import ir.maktab32.java.homeworks.hw10articles.repositories.db2.UserRepository;
import ir.maktab32.java.homeworks.hw10articles.share.AuthenticationService;
import ir.maktab32.java.homeworks.hw10articles.utilities.CurrentUserStatus;

import java.util.List;
import java.util.Scanner;

public class SignInUseCaseImpl implements SignInUseCase {
    @Override
    public User execute() {
        User result;
        User validateResult = inputAndValidation();
        if (validateResult != null){
            System.out.println("\t\t\u2705 Sign In Successful!");
            result = validateResult;
            AuthenticationService.getInstance().setSignedInUser(result);
        }
        else {
            System.out.println("\t\t\u26a0 Sign In Failed!");
            result = null;
        }
        return result;
    }

    private User inputAndValidation(){
        User result = null;

        Scanner scanner = new Scanner(System.in);

        if (CurrentUserStatus.isSignedIn())
            System.out.println("\t\u26a0 You Are Signed In Already!" );
        else {
            System.out.print("\t\u29bf Username: ");
            String username = scanner.nextLine();
            System.out.print("\t\u29bf Password: ");
            String password = scanner.nextLine();

            List<User> allUsers = UserRepository.getInstance().findAll();
            User requestedUser = null;
            for (User i : allUsers){
                if (i.getUsername().equals(username)){
                    requestedUser = i;
                    break;
                }
            }
            if (requestedUser == null)
                System.out.println("\t\u26a0 This Username Doesn't Exist!");
            else if (!requestedUser.getPassword().equals(password))
                System.out.println("\t\u26a0 Invalid Password!");
            else
                result = requestedUser;
        }
        return result;
    }
}
