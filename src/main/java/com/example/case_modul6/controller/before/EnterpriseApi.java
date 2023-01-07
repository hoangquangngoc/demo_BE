package com.example.case_modul6.controller.before;

import com.example.case_modul6.model.before.*;
import com.example.case_modul6.model.before.Notification.NotificationEnterprise;

import com.example.case_modul6.service.before.InterfaceService.All.*;
import com.example.case_modul6.service.before.SendMailService;
import com.example.case_modul6.service.before.impl.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/enterprise")
public class EnterpriseApi {
    @Autowired
    IPostEnterpriseService postEnterpriseService;

    @Autowired
    IEnterpriseService enterpriseService;



    @Autowired
    AppUserService appUserService;

    @Autowired
    INotificationEnterpriseService notificationEnterpriseService;

    @Autowired
    IUserApplyService userApplyService;

    @Autowired
    SendMailService sendMailService;


    @GetMapping("/findAll")
    public ResponseEntity<List<PostEnterprise>> findAllPostEnterprise() {
        return new ResponseEntity<>(postEnterpriseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findAllByIdEnterprise/{id}")
    public ResponseEntity<List<PostEnterprise>> findAllByIdEnterprise(@PathVariable int id) {
        return new ResponseEntity<>(postEnterpriseService.findAllByIdEnterprise(id), HttpStatus.OK);
    }

    @PostMapping("/savePost")
    public ResponseEntity<PostEnterprise> savePostEnterprise(@RequestBody PostEnterprise postEnterprise) {
        postEnterpriseService.save(postEnterprise);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/findEnterpriseId/{id}")
    public ResponseEntity<Enterprise> getEnterpriseById(@PathVariable int id) {
        return new ResponseEntity<>(enterpriseService.findEnterpriseById(id), HttpStatus.OK);
    }
    @GetMapping("/findEnterprise/{name}")
    public ResponseEntity<Enterprise> getEnterpriseByName(@PathVariable String name) {
        return new ResponseEntity<>(enterpriseService.findByGmailEnterprise(name), HttpStatus.OK);
    }

    @GetMapping("/findAllFormJob")
    public ResponseEntity<List<FormJob>> listFormJob() {
        return new ResponseEntity<>(postEnterpriseService.findAllFormJob(), HttpStatus.OK);
    }
    @GetMapping("/findAllRegime")
    public ResponseEntity<List<Regime>> listRegime() {
        return new ResponseEntity<>(postEnterpriseService.findAllRegime(), HttpStatus.OK);
    }

    @GetMapping("/listPostVipByEnterprise/{id}")
    public ResponseEntity<List<PostEnterprise>> listPostVipByEnterprise(@PathVariable int id) {
        return new ResponseEntity<>(postEnterpriseService.listPostVipByEnterprise(id), HttpStatus.OK);
    }
    @GetMapping("listPostThuongByEnterprise/{id}")
    public ResponseEntity<List<PostEnterprise>> listThuongVipByEnterprise(@PathVariable int id) {
        return new ResponseEntity<>(postEnterpriseService.listPostThuongByEnterprise(id), HttpStatus.OK);
    }
    @GetMapping("/statusPost/{id}")
    public ResponseEntity<PostEnterprise> statusPost(@PathVariable int id) {
        postEnterpriseService.statusPost(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("/openKeyPost/{id}")
    public ResponseEntity<PostEnterprise> openKeyPost(@PathVariable int id){
        postEnterpriseService.openKeyPost(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("/findPostById/{id}")
    public ResponseEntity<PostEnterprise> findPostById(@PathVariable int id){
        return new ResponseEntity<>(postEnterpriseService.findById(id), HttpStatus.OK);
    }
    @PostMapping("/editpost")
    public ResponseEntity<PostEnterprise> editPostEnterprise(@RequestBody PostEnterprise postEnterprise) {
        postEnterpriseService.editPost(postEnterprise);
            return new ResponseEntity<>(HttpStatus.OK);
        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//    Thông bao đến doanh nghiệp sau khi có user apply

    @GetMapping("/listNotiyApply/{idEnterprise}")
    public ResponseEntity<List<NotificationEnterprise>> listNotiyApply(@PathVariable int idEnterprise){
        return new ResponseEntity<>(notificationEnterpriseService.notificationEnterpriseSByEnterprise(idEnterprise),HttpStatus.OK);
    }
//confim cv của user
     @PostMapping("/confirmUserApply/{id}")
     public ResponseEntity<NotificationEnterprise> confirmUserApply(@PathVariable int id){
          notificationEnterpriseService.updateStatusNotifi(id);
          int idUserApply= notificationEnterpriseService.findById(id).getId();
          userApplyService.updateStatusConfirmUserApply(idUserApply);
          String mailUserApply = userApplyService.findById(idUserApply).getMailCv();
          String nameEnterToMail=  notificationEnterpriseService.findById(id).getEnterprise().getNameEnterprise();
          String nameJob = userApplyService.findById(id).getPostEnterprise().getNamePostEnterprise();
          String vtJob = userApplyService.findById(id).getPostEnterprise().getVacanciesPostEnterprise();
          sendMailService.sendMail(mailUserApply,"Công ty "+nameEnterToMail+" thông báo ","\n\t\t Cv ứng tuyển công viêc "+nameJob+" ví trí "+vtJob+" bạn đã được" +
                  "công ty chúng tôi xác nhận \n\t\t\t\t -Xin lòng để ý điện thoại khi chúng tôi liên hệ !\n\t Xin cảm ơn ! ");
       return  new ResponseEntity<>(HttpStatus.OK);
    }
//    ListAppLy theo bài đăng !
    @GetMapping("/allUserApplyByIdPost/{idPost}")
    public ResponseEntity<List<UserApply>> allUserApplyByIdPost(@PathVariable int idPost){
        for (int i=0;i<userApplyService.listUserApplyByIdPost(idPost).size();i++){
            System.out.println(userApplyService.listUserApplyByIdPost(idPost).get(i));
        }
         return new ResponseEntity<>(userApplyService.listUserApplyByIdPost(idPost),HttpStatus.OK);
    }
//tìm ra dối tượng apply theo id
    @GetMapping("/userApplyById/{id}")
    public ResponseEntity<UserApply> getUserApplyById(@PathVariable int id){
        return new ResponseEntity<>(userApplyService.findById(id),HttpStatus.OK);
    }


//    xóa đi những bài post khi hết hạn !
    @GetMapping("/deletePostExpired")
    public ResponseEntity<Boolean> deletePostExpired(){
          postEnterpriseService.deletePostExpired();
          return new ResponseEntity<>(HttpStatus.OK);
    }

}
