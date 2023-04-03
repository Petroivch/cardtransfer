package com.example.coursach.controller;

import com.example.coursach.dto.TransactionDto;
import com.example.coursach.repository.CardRepository;
import com.example.coursach.service.TransferService;
import com.example.coursach.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TransferController {
    private final TransferService service;
    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;

    @Autowired
    public TransferController(TransferService service,
                              CardRepository cardRepository,
                              TransferRepository transferRepository) {
        this.service = service;
        this.cardRepository = cardRepository;
        this.transferRepository = transferRepository;

    }

    //корень сайта
    @GetMapping(path = "/")
    public String Home() {
        return "transfer";
    }

    //вывод результата выполнения операции (обработка значений, полученных из формы)
    @PostMapping(path = "/transfer")
    public String transfer(@ModelAttribute TransactionDto transactionDto, Model model){
        boolean res = service.makeTransfer(transactionDto);
        model.addAttribute("result", res);
        return "transfer_result";
    }

    //вывод списка переводов
    @GetMapping(path = "/transfers")
    public String transfers(Model model){
        model.addAttribute("transferslist", transferRepository.findAll());
        return "transfers";
    }

    //вывод списка карт
    @GetMapping(path = "/cards")
    public String cards(Model model) {
        model.addAttribute("cardslist", cardRepository.findAll());
        return "cards";
    }

}
