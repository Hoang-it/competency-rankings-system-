package com.fa.training.demo.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handle exception when the file is bigger than 2MB
     * It's used to catch an exception of the changeAvatar() method
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String uploadFileException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "The File is too large!");
        return "redirect:/employee/change-avatar";
    }
}
