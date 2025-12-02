package use_case.manage_application;

import entity.Application;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManageApplicationInteractorTest {

    // Fake DAO for testing
    static class FakeDAO implements ManageApplicationDataAccessInterface {
        List<Application> apps = new ArrayList<>();

        @Override
        public List<Application> getAllApplications() {
            return apps;
        }

        @Override
        public Application getApplicationById(String id) {
            return apps.stream().filter(a -> a.getApplicationId().equals(id)).findFirst().orElse(null);
        }

        @Override
        public void updateStatus(Application application, String status) {
            application.setStatus(status);
        }
    }

    // Fake Presenter for testing
    static class FakePresenter implements ManageApplicationOutputBoundary {
        ManageApplicationOutputData captured;

        @Override
        public void present(ManageApplicationOutputData outputData) {
            captured = outputData;
        }
    }

    @Test
    void execute_callsDAOAndPresenterCorrectly() {
        FakeDAO dao = new FakeDAO();
        FakePresenter presenter = new FakePresenter();

        Application app1 = new Application("eeee12394059", "John", "Doe", "jd@mail.com",
                "123456", "30", "Engineer", "pet123", "I love pets", "Big house", "Full time", "None");
        dao.apps.add(app1);

        ManageApplicationInteractor interactor = new ManageApplicationInteractor(dao, presenter);

        interactor.execute();

        // Verify presenter got the same applications
        assertNotNull(presenter.captured);
        assertEquals(1, presenter.captured.getApplications().size());
        assertEquals("John", presenter.captured.getApplications().get(0).getFirstName());
    }
}
