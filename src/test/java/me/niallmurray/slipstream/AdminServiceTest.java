package me.niallmurray.slipstream;

import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.service.AdminService;
import me.niallmurray.slipstream.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
class AdminServiceTest {
  @Autowired
  private AdminService adminService;
  @MockBean
  private UserService userService;

  @Test
  void testGetAllUserAccounts() {
    ArrayList<User> userList = new ArrayList<>();
    when(userService.findAll()).thenReturn(userList);
    List<User> actualAllUserAccounts = adminService.getAllUserAccounts();
    assertSame(userList, actualAllUserAccounts);
    assertTrue(actualAllUserAccounts.isEmpty());
    verify(userService).findAll();
  }
}

