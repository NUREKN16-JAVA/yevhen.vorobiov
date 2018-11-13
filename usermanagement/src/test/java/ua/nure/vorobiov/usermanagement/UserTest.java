package ua.nure.vorobiov.usermanagement;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserTest {

    private static final long ID = 1L;
    private static final String FIRST_NAME = "Иван";
    private static final String LAST_NAME = "Иванов";
    private static final String DATE_STRING = "29-04-2018";
    private static final String DATE_PATTERN = "dd-MM-yyyy";
    private static final int TEST_BIRTH_YEAR = 1958;
    private static final String FULL_NAME_SEPARATOR = ", ";
    private User user;
    private Calendar calendar;
    private Date dateOfBirth;

    @Before
    public void setUp() throws ParseException {
        user = new User(ID, FIRST_NAME, LAST_NAME, new SimpleDateFormat(DATE_PATTERN).parse(DATE_STRING));
        calendar = Calendar.getInstance();
    }

    @Test
    public void testGetFullName() {
        String expectedResult = LAST_NAME + FULL_NAME_SEPARATOR + FIRST_NAME;

        String actualResult = user.getFullName();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAgeWhenMonthAreSame() {
        int expectedResult = 60;

        calendar.set(TEST_BIRTH_YEAR, calendar.get(Calendar.MONTH), 1);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);

        int actualResult = user.getAge();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAgeWhenMonthOfBirthIsAfterCurrentMonth() {
        int expectedResult = 59;

        calendar.add(Calendar.MONTH, 1);
        calendar.set(TEST_BIRTH_YEAR, calendar.get(Calendar.MONTH), 20);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);

        int actualResult = user.getAge();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAgeWhenBirthdayIsAfterCurrentDay() {
        int expectedResult = 59;

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(TEST_BIRTH_YEAR, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);

        int actualResult = user.getAge();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAgeWhenMonthAndDayOfBirthEqualsToCurrentDate() {
        int expectedResult = 60;

        calendar.set(TEST_BIRTH_YEAR, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);

        int actualResult = user.getAge();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAgeWhenMonthOfBirthIsBeforeCurrentMonth() {
        int expectedResult = 60;

        calendar.set(TEST_BIRTH_YEAR, calendar.get(Calendar.MONTH) - 1, 20);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);

        int actualResult = user.getAge();

        assertEquals(expectedResult, actualResult);
    }
}