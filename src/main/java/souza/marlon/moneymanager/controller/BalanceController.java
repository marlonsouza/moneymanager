package souza.marlon.moneymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import souza.marlon.moneymanager.dto.UserBalanceDto;
import souza.marlon.moneymanager.service.UserBalanceService;

import java.util.List;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final UserBalanceService userBalanceService;

    @Autowired
    public BalanceController(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    @PostMapping
    public UserBalanceDto createBalance(@RequestBody UserBalanceDto userBalanceDto){
        return userBalanceService.createBalance(userBalanceDto);
    }

    @GetMapping
    public List<UserBalanceDto> getBalanceList(){
        return userBalanceService.getBalance();
    }

}
