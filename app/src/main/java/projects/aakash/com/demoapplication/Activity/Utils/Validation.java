package projects.aakash.com.demoapplication.Activity.Utils;

import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pankaj on 20-07-2017.
 */

public class Validation {
    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "^[789]\\d{9}$";
    // Error Messages
    private static final String REQUIRED_MSG =  "cannot be empty";
    private static final String EMAIL_MSG = "Please enter valid Email Id.";
    private static final String PHONE_MSG = "Please enter 10 digit numeric mobile number.";
    private static final String PASS_MES = "Please enter minimum 4 digit characters.";
    private static final String NOT_MATCH = "Password not matched";
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,20}$";

    private static Pattern pattern;
    private static Matcher matcher;


    //To check unique user name

    public static boolean validate(final String username){
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();

    }
    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    public static boolean isEmailAddressForEditProfile(EditText editText, boolean required) {
        return isValidEditProfile(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    private static boolean isValidEditProfile(EditText editText, String emailRegex, String emailMsg, boolean required) {
        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);
        editText.setFocusable(true);
        /*// text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;*/

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(emailRegex, text)) {
            editText.setError(emailMsg);
            return false;
        };

        return true;
    }
    // call this method when you need to check phone number validation

    public static boolean isValidMobile(EditText editText, boolean required)
    {
        boolean check;
        String errMsg = "Please enter 10 digit numeric mobile number.";

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        if(text.length() < 10 || text.length() > 13)
        {
            editText.setError(errMsg);
            check = false;
        }
        else
        {
            check = true;
        }
        return check;
    }

    public static boolean isPasswordMatching(String password, String confirmPassword, EditText editText) {
        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {
            // do your Toast("passwords are not matching");
            editText.setError(NOT_MATCH);
            return false;
        }

        return true;
    }

    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);
        editText.setFocusable(true);
        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);
        editText.setFocusable(true);
        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    public static boolean hasPincode(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() <  5) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
    public static boolean hasPassword(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text

        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }else if(text.length() < 4){
            editText.setError(PASS_MES);
            return false;
        }

        return true;
    }

    public static boolean hasTextForDate(TextView etWishDate) {
        String text = etWishDate.getText().toString().trim();
        etWishDate.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            etWishDate.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}
