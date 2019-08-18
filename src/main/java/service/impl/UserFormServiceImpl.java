package service.impl;

import model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import service.UploadFileService;

import java.io.File;
import java.io.IOException;

public class UserFormServiceImpl implements UploadFileService<UserForm> {

    @Autowired
    Environment env;

    @Override
    public String fileName(UserForm userForm) {
        MultipartFile multipartFile = userForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = this.env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException var7) {
            var7.printStackTrace();
        }
        return fileName;
    }
}
