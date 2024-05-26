package uz.primepicks.primepicks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.primepicks.primepicks.entity.AttachmentContent;
import uz.primepicks.primepicks.repo.AttachmentContentRepo;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentContentRepo attachmentContentRepo;

    @PostMapping("/set/{id}")
    public String set(@PathVariable("id") UUID id, @RequestParam(name = "photo") MultipartFile photo) throws IOException {
        AttachmentContent attachmentContentRepoById = attachmentContentRepo.findAttachmentContentByAttachmentId(id);
        attachmentContentRepoById.setContent(photo.getBytes());
        attachmentContentRepo.save(attachmentContentRepoById);
        return "redirect:/admin";
    }
}
