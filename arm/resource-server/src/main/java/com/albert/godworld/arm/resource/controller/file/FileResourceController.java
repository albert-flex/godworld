package com.albert.godworld.arm.resource.controller.file;

import com.albert.godworld.arm.resource.domain.file.FileResource;
import com.albert.godworld.arm.resource.service.file.FileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/file")
public class FileResourceController {

    private final FileResourceService fileResourceService;

    @Value("${app.file-resource.prefix}")
    private String prefix;

    @Autowired
    public FileResourceController(FileResourceService fileResourceService) {
        this.fileResourceService = fileResourceService;
    }

    @PostMapping
    public FileResource create(@RequestBody FileResource fileResource) {
        fileResourceService.save(fileResource);
        return fileResource;
    }

    @GetMapping("/lib_and_own/{lib}/{ownId}")
    public FileResource getByLibAndOwnId(@PathVariable("lib") String lib, @PathVariable("ownId") Long ownId) {
        return fileResourceService.getByLibAndOwnId(lib, ownId);
    }

    @PostMapping("/upload")
    public Long upload(@RequestParam("file") MultipartFile file,
                       @RequestParam("name") String name,
                       @RequestParam("lib") String lib,
                       @RequestParam("ownId") Long ownId) throws IOException {
        File dir = new File("." + this.prefix);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String name0 = file.getOriginalFilename();
        if (name0 == null || name0.isEmpty()) return -1L;

        String format = name0.split("\\.")[1];

        FileResource resource = new FileResource();
        resource.setName(name);
        resource.setFormat(format);
        resource.setLib(lib);
        resource.setOwnId(ownId);

        fileResourceService.clearResource(lib, ownId);
        fileResourceService.save(resource);
        file.transferTo(new File(dir.getAbsoluteFile() + File.separator + resource.getId() + "." + format));
        return resource.getId();
    }

    @GetMapping("/download2")
    public void download2(@RequestParam("lib") String lib,
                          @RequestParam("ownId") Long ownId, HttpServletResponse response) {
        FileResource fileResource = fileResourceService.getByLibAndOwnId(lib, ownId);
        _down(fileResource, response);
    }

    @GetMapping("/{id}")
    public void download(
            @PathVariable("id") Long id, HttpServletResponse response) {

        _down(fileResourceService.getById(id), response);
    }

    private void defaultP(HttpServletResponse response) {
        BufferedInputStream fis = null;
        OutputStream os = null;
        try {
            response.setCharacterEncoding("UTF-8");
            fis = new BufferedInputStream(Objects.requireNonNull(FileResourceController.class.getResourceAsStream("/file/1.png")));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            os = new BufferedOutputStream(response.getOutputStream());
            String f = "png";
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "attachment;filename=1.png");
            os.write(buffer);
            os.flush();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void _down(FileResource resource, HttpServletResponse response) {
        if (resource == null) {
            defaultP(response);
            return;
        };

        BufferedInputStream fis = null;
        OutputStream os = null;
        try {
            String path = "." + this.prefix + File.separator + resource.getId() + "." + resource.getFormat();
            String fileName = resource.getName();
            response.setCharacterEncoding("UTF-8");
            fis = new BufferedInputStream(Files.newInputStream(Paths.get(path)));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            os = new BufferedOutputStream(response.getOutputStream());
            String f = resource.getFormat();
            boolean isPicture = f.equals("png")
                    || f.equals("jpeg")
                    || f.equals("jpg")
                    || f.equals("gif")
                    || f.equals("bmp");
            if (isPicture) {
                response.setContentType("image/" + f);
            } else {
                response.setContentType("application/octet-stream");
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "." + resource.getFormat());
            os.write(buffer);
            os.flush();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
