package me.niallmurray.slipstream.web;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.dto.DriverStanding;
import me.niallmurray.slipstream.dto.DriverStandingResponse;
import me.niallmurray.slipstream.dto.StandingsList;
import me.niallmurray.slipstream.security.ActiveUserStore;
import me.niallmurray.slipstream.service.AdminService;
import me.niallmurray.slipstream.service.DriverService;
import me.niallmurray.slipstream.service.LeagueService;
import me.niallmurray.slipstream.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Controller
public class AdminController {
  @Autowired
  private ActiveUserStore activeUserStore;
  @Autowired
  private TeamService teamService;
  @Autowired
  private LeagueService leagueService;
  @Autowired
  private DriverService driverService;
  @Autowired
  private AdminService adminService;
  @Value("${ergast.urls.base}${ergast.urls.currentDriverStandings}.json")
  private String f1DataApi;

  @GetMapping("/admin")
  public String getAdmin(ModelMap modelMap) {
    List<User> allUserAccounts = adminService.getAllUserAccounts();
    modelMap.addAttribute("users", allUserAccounts);
    modelMap.addAttribute("leagues", leagueService.findAll());
    modelMap.addAttribute("activeUsers", activeUserStore.getUsers());
    modelMap.addAttribute("allDrivers", driverService.sortDriversStanding());
    modelMap.addAttribute("isLoggedIn", true);
    modelMap.addAttribute("isAdmin", true);
    return "admin";
  }

  @ResponseBody
  @GetMapping("admin/driverStandingsJSON")
  public ResponseEntity<DriverStandingResponse> getDriverStandingsResponse() {
    return new RestTemplate().getForEntity(f1DataApi, DriverStandingResponse.class);
  }

  public List<Driver> getDriversFromResponse() {
    List<StandingsList> standingsLists = Objects.requireNonNull(getDriverStandingsResponse().getBody())
            .mRData.standingsTable
            .standingsLists;
    List<DriverStanding> currentStandings = standingsLists.listIterator().next().driverStandings;

    return driverService.mapDTOToDrivers(currentStandings);
  }

  @PostMapping("/admin/addDrivers")
  public String getAddDrivers(ModelMap modelMap) {
    driverService.addDrivers(getDriversFromResponse());

    modelMap.addAttribute("allDrivers", driverService.sortDriversStanding());
    return "redirect:/admin";
  }

  // To automatically add drivers when first user attempts login,
  // if admin role has not already added drivers.
  // Admin can manually add drivers using PostMapping above.
  public void addDrivers() {
    driverService.addDrivers(getDriversFromResponse());
  }

  @PostMapping("/admin/updateDrivers")
  public String getUpdateDriverStandings(ModelMap modelMap) {
    driverService.updateDrivers(getDriversFromResponse());

    for (League league : leagueService.findAll()) {
      teamService.updateLeagueTeamsRankings(league);
    }
    modelMap.addAttribute("allDrivers", driverService.sortDriversStanding());
    return "redirect:/admin";
  }
}