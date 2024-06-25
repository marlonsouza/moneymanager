package souza.marlon.moneymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import souza.marlon.moneymanager.dto.UserBalanceDto;
import souza.marlon.moneymanager.service.UserBalanceService;

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

}
