package uz.alfabu.bookrecommendationapp.utils;

import lombok.experimental.UtilityClass;
import uz.alfabu.bookrecommendationapp.exception.MyException;

@UtilityClass
public class ThrowUtil {
    public void throwIf(boolean condition, MyException e) {
        if (condition)
            throw e;
    }

    public void throwIfNot(boolean condition, MyException e) {
        throwIf(!condition, e);
    }
}
