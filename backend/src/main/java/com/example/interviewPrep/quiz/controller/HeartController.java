package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.dto.HeartRequestDTO;
import com.example.interviewPrep.quiz.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_CREATED;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_OK;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_SERVER_ERROR;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://52.3.173.210")
@Log4j2
public class HeartController {
    private final HeartService heartService;

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody @NotNull HeartRequestDTO heartDTO) {

        try {
            heartService.deleteHeart(heartDTO);
            return RESPONSE_OK;
        } catch (Exception e) {
            return RESPONSE_SERVER_ERROR;
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @NotNull HeartRequestDTO heartDTO) {

        try {
            heartService.createHeart(heartDTO);
            return RESPONSE_CREATED;
        } catch (Exception e) {
            log.error("error is" + e.getMessage());
            return RESPONSE_SERVER_ERROR;
        }
    }

}
