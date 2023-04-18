package me.niallmurray.slipstream.web;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.service.DriverService;
import me.niallmurray.slipstream.service.LeagueService;
import me.niallmurray.slipstream.service.TeamService;
import me.niallmurray.slipstream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

  @Autowired
  private UserService userService;
  @Autowired
  private TeamService teamService;
  @Autowired
  private DriverService driverService;
  @Autowired
  private LeagueService leagueService;

  @GetMapping("/dashboard")
  public String redirectUserDashboard(@AuthenticationPrincipal User userAuth) {
    return "redirect:/dashboard/" + userAuth.getUserId();
  }

  @GetMapping("/dashboard/{userId}")
  public String getDashboard(@PathVariable Long userId, ModelMap modelMap) {
    User user = userService.findById(userId);
    List<Team> allTeams = teamService.getAllTeams();
    League currentLeague = leagueService.findNewestLeague();

    if (currentLeague.getTeams().size() != 0
            && currentLeague.getTeams().size() % 10 == 0) {
      currentLeague = leagueService.createLeague();
    }

    modelMap.addAttribute("user", user);
    modelMap.addAttribute("driver", new Driver());
    modelMap.addAttribute("allTeams", allTeams);
    modelMap.addAttribute("currentLeague", currentLeague);
    modelMap.addAttribute("teamsInLeague", currentLeague.getTeams());
    modelMap.addAttribute("teamsByPick", teamService.getAllTeamsByNextPick());
    modelMap.addAttribute("allDrivers", driverService.sortDriversStanding());
    modelMap.addAttribute("leagueFull", false);
    modelMap.addAttribute("timeToPick", false);

    // Change view depending on if user has created a team
    // Also handles NPEs.
    if (user.getTeam() == null) {
      modelMap.addAttribute("teamLeague", currentLeague);
      modelMap.addAttribute("availableDrivers", driverService.getUndraftedDrivers(currentLeague));
      modelMap.addAttribute("currentPickNumber", teamService.getCurrentPickNumber(currentLeague));
      modelMap.addAttribute("teamsByRank", teamService.updateLeagueTeamsRankings(currentLeague));
    } else {
      modelMap.addAttribute("teamLeague", user.getTeam().getLeague());
      modelMap.addAttribute("availableDrivers", driverService.getUndraftedDrivers(user.getTeam().getLeague()));
      modelMap.addAttribute("currentPickNumber", teamService.getCurrentPickNumber(user.getTeam().getLeague()));
      modelMap.addAttribute("teamsByRank", teamService.updateLeagueTeamsRankings(user.getTeam().getLeague()));
    }

    if (userService.isLoggedIn(user)) {
      modelMap.addAttribute("isLoggedIn", true);
    }
    if (userService.isAdmin(user)) {
      modelMap.addAttribute("isAdmin", true);
      modelMap.addAttribute("teamsByRank", teamService.updateLeagueTeamsRankings(currentLeague));

    }
// Currently 10 players per league.
// After league is full, new users are added to new league.
    if (!userService.isAdmin(user) && user.getTeam() != null) {
      if (user.getTeam().getLeague().getTeams().size() >= 10) {
        modelMap.addAttribute("leagueFull", true);
        modelMap.addAttribute("nextUserPick", teamService.getNextToPick(user.getTeam().getLeague()));
      }
      if (teamService.timeToPick(user.getTeam().getLeague(), user.getTeam().getTeamId())) {
        modelMap.addAttribute("timeToPick", true);
      }
    }
    return "dashboard";
  }

  @PostMapping("/dashboard/{userId}")
  public String postCreateTeam(@PathVariable Long userId, User user) {
    // Check for unique team names.
    String teamName = user.getTeam().getTeamName();
    if (teamService.teamNameExists(teamName)) {
      Team team = new Team();
      user = userService.findById(userId);
      if (user.getTeam() == null) {
        user.setTeam(team);
      }
      user.getTeam().setTeamName(teamName);
      teamService.createTeam(user);
      return "redirect:/dashboard/" + userId;
    }
    return "redirect:/dashboard/%d?error".formatted(userId);
  }

  @PostMapping("/dashboard/{userId}/draftPick")
  public String postMakePick(@PathVariable Long userId, Driver driver) {
    if (driver.getDriverId() == null) {
      return "redirect:/dashboard/%d?error".formatted(userId);
    }

    Long driverId = driver.getDriverId();
    teamService.addDriverToTeam(userId, driverId);

    return "redirect:/dashboard/" + userId;
  }

}


