package com.example.coursach.service;

import com.example.coursach.repository.CardRepository;
import com.example.coursach.repository.TransferRepository;
import com.example.coursach.repository.UserRepository;
import com.example.coursach.entity.Card;
import com.example.coursach.entity.Transfer;
import com.example.coursach.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransferService {

    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransferService(CardRepository cardRepository, TransferRepository transferRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.transferRepository = transferRepository;
    }


    //основная функция для создания перевода (проверки, создание, апдейт)
    public boolean makeTransfer(TransactionDto tDto) {
        boolean res = false;

        Transfer transfer;

        //проверки на существование введенных карт, если Id=0, то карта не найдена
        int cardSourceId = checkCard(tDto.getCard_source(), tDto.getCard_source_exp_date(), tDto.getCard_source_cvv());
        int cardDestinationId = checkCard(tDto.getCard_destination());
        System.out.println(cardDestinationId);
        System.out.println(cardSourceId);
        if ((cardSourceId != 0) && (cardDestinationId !=0)){
            transfer = new Transfer();
            transfer.setCardSource(cardRepository.findCardById(cardSourceId));
            transfer.setCardDestination(cardRepository.findCardById(cardDestinationId));
            transfer.setTransaction_date(new java.sql.Date(new Date(System.currentTimeMillis()).getTime()));
            transfer.setAmount(Double.valueOf(tDto.getAmount()));
            transfer.setResult(false);
            transferRepository.save(transfer); //создаем новый перевод
            if (update_cards(cardSourceId, cardDestinationId, Double.valueOf(tDto.getAmount()))){
                //если перевод успешен, то меняем его статус и апдейтим
                transfer.setResult(true);
                transferRepository.save(transfer);
            }
            res = transfer.isResult();
        }

        return res;
    }

    //апдейт сумм на картах
    public boolean update_cards(int cardSourceId, int cardDestinationId, Double amount){
        boolean res = false;

        List<Card> cards = cardRepository.findAll();
        for (Card card : cards) {
            if (card.getId() == cardSourceId) {
                if (card.getAmount() >= amount) {
                    card.setAmount(card.getAmount() - amount);
                    for (Card card1 : cards) {
                        if (card1.getId() == cardDestinationId) {
                            card1.setAmount(card1.getAmount() + amount);
                            res = true;
                        }
                    }
                }
            }
        }
        cardRepository.saveAll(cards);
        return res;
    }

    //проверка существования карты со всеми параметрами
    public int checkCard(String number_check, String exp_date_check, int cvv_check){
        int res = 0;
        Card card = cardRepository.findCardByNumberAndCvv(number_check, cvv_check);
        if (card != null) {
            res = card.getId();
        }
        return res;
    }

    //проверка существования карты только по номеру (для destination карты)
    public int checkCard(String number_check){
        int res = 0;
        Card card = cardRepository.findCardBynumber(number_check);
        if (card != null) {
            res = card.getId();
        }
        return res;
    }
}
