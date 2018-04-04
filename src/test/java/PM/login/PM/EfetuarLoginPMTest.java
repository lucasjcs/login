package PM.login.PM;

import PM.login.model.UserType;
import PM.login.model.User;
import PM.login.DAO.UserDAO;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author andreendo
 */
public class EfetuarLoginPMTest {

    public EfetuarLoginPMTest() {
    }

    @Test
    public void testClear() {
        PerformLoginPM efetuarLoginPM = new PerformLoginPM();
        efetuarLoginPM.setLogin("andre");
        efetuarLoginPM.setPassword("123");

        efetuarLoginPM.clear();

        assertEquals("", efetuarLoginPM.getLogin());
        assertEquals("", efetuarLoginPM.getPassword());
    }

    @Test
    public void testEmptyFields() {
        PerformLoginPM efetuarLoginPM = new PerformLoginPM();
        efetuarLoginPM.setLogin("");
        efetuarLoginPM.setPassword("");

        try {
            efetuarLoginPM.pressLogin();
            fail();
        } catch (Exception e) {
            assertEquals("Empty fields", e.getMessage());
        }
    }

    @Test
    public void testInexistentUsername() {
        UserDAO userDaoMock = mock(UserDAO.class);
        when(userDaoMock.getByName("andre"))
                .thenReturn(null);

        PerformLoginPM efetuarLoginPM = new PerformLoginPM();
        efetuarLoginPM.setUserDao(userDaoMock);
        efetuarLoginPM.setLogin("andre");
        efetuarLoginPM.setPassword("123");

        try {
            efetuarLoginPM.pressLogin();
            fail();
        } catch (Exception e) {
            assertEquals("Inexistent username", e.getMessage());
        }
    }

    @Test
    public void testWrongPassword() {
        UserDAO userDaoMock = mock(UserDAO.class);
        when(userDaoMock.getByName("andre"))
                .thenReturn(new User("andre", "1234", UserType.NORMALUSER));

        PerformLoginPM efetuarLoginPM = new PerformLoginPM();
        efetuarLoginPM.setLogin("andre");
        efetuarLoginPM.setPassword("123");

        efetuarLoginPM.setUserDao(userDaoMock);

        try {
            efetuarLoginPM.pressLogin();
            fail();
        } catch (Exception e) {
            assertEquals("Wrong password", e.getMessage());
        }
    }

    @Test
    public void testAdminUserLogin() throws Exception {
        UserDAO userDaoMock = mock(UserDAO.class);
        when(userDaoMock.getByName("admin"))
                .thenReturn(new User("admin", "admin", UserType.ADMIN));

        PerformLoginPM efetuarLoginPM = new PerformLoginPM();
        efetuarLoginPM.setLogin("admin");
        efetuarLoginPM.setPassword("admin");

        efetuarLoginPM.setUserDao(userDaoMock);

        PagePM pagePM = efetuarLoginPM.pressLogin();
        assertTrue(pagePM instanceof AdminMainPagePM);
        assertEquals("admin", pagePM.getLoggedUser().getUsername());
    }

    @Test
    public void testNormalUserLogin() throws Exception {
        UserDAO userDaoMock = mock(UserDAO.class);
        when(userDaoMock.getByName("user"))
                .thenReturn(new User("user", "normal", UserType.NORMALUSER));

        PerformLoginPM efetuarLoginPM = new PerformLoginPM();
        efetuarLoginPM.setLogin("user");
        efetuarLoginPM.setPassword("normal");

        efetuarLoginPM.setUserDao(userDaoMock);

        PagePM pagePM = efetuarLoginPM.pressLogin();
        assertTrue(pagePM instanceof NormalUserMainPagePM);
        assertEquals("user", pagePM.getLoggedUser().getUsername());
    }

    @Test
    public void testUsuarioBloqueado() throws Exception {
        UserDAO userDaoMock = mock(UserDAO.class);
        
        when(userDaoMock.getByName("user"))
                .thenReturn(new User("user", "normal", UserType.NORMALUSER, status));

        PerformLoginPM efetuarLoginPM = new PerformLoginPM();
        efetuarLoginPM.setLogin("user");
        efetuarLoginPM.setPassword("normal");
        assertEquals("Usuario bloqueado", efetuarLoginPM.verificaStatusUsuario(userDaoMock.getByName("user").getBloqueado()));
//        boolean status = efetuarLoginPM.verificaStatusUsuario(userDaoMock.getByName("user").getStatus());
        
        efetuarLoginPM.setUserDao(userDaoMock);

        PagePM pagePM = efetuarLoginPM.pressLogin();
        assertTrue(pagePM instanceof NormalUserMainPagePM);
        assertEquals("user", pagePM.getLoggedUser().getUsername());
    }
}
