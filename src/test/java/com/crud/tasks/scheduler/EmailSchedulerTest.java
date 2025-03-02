package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmailSchedulerTest {

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Test
    void shouldSendInformationEmail() {
        //Given
        when(adminConfig.getAdminMail()).thenReturn("admin@example.com");
        when(taskRepository.count()).thenReturn(5L);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(argThat(mail ->
                mail.getMailTo().equals("admin@example.com") &&
                mail.getSubject().equals("Tasks: Once a day email") &&
                mail.getMessage().equals("Currently in database you got: 5 tasks") &&
                mail.getToCc().equals("robertointerwento@gmail.com")
        ));
    }
}