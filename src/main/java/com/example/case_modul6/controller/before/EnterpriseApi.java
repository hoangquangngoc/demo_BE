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
//    Th??ng bao ?????n doanh nghi???p sau khi c?? user apply

    @GetMapping("/listNotiyApply/{idEnterprise}")
    public ResponseEntity<List<NotificationEnterprise>> listNotiyApply(@PathVariable int idEnterprise){
        return new ResponseEntity<>(notificationEnterpriseService.notificationEnterpriseSByEnterprise(idEnterprise),HttpStatus.OK);
    }
//confim cv c???a user
     @PostMapping("/confirmUserApply/{id}")
     public ResponseEntity<NotificationEnterprise> confirmUserApply(@PathVariable int id){
          notificationEnterpriseService.updateStatusNotifi(id);
          int idUserApply= notificationEnterpriseService.findById(id).getId();
          userApplyService.updateStatusConfirmUserApply(idUserApply);
          String mailUserApply = userApplyService.findById(idUserApply).getMailCv();
          String nameEnterToMail=  notificationEnterpriseService.findById(id).getEnterprise().getNameEnterprise();
          String nameJob = userApplyService.findById(id).getPostEnterprise().getNamePostEnterprise();
          String vtJob = userApplyService.findById(id).getPostEnterprise().getVacanciesPostEnterprise();
          sendMailService.sendMail(mailUserApply,"C??ng ty "+nameEnterToMail+" th??ng b??o ","\n\t\t Cv ???ng tuy???n c??ng vi??c "+nameJob+" v?? tr?? "+vtJob+" b???n ???? ???????c" +
                  "c??ng ty ch??ng t??i x??c nh???n \n\t\t\t\t -Xin l??ng ????? ?? ??i???n tho???i khi ch??ng t??i li??n h??? !\n\t Xin c???m ??n ! ");
       return  new ResponseEntity<>(HttpStatus.OK);
    }
//    ListAppLy theo b??i ????ng !
    @GetMapping("/allUserApplyByIdPost/{idPost}")
    public ResponseEntity<List<UserApply>> allUserApplyByIdPost(@PathVariable int idPost){
        for (int i=0;i<userApplyService.listUserApplyByIdPost(idPost).size();i++){
            System.out.println(userApplyService.listUserApplyByIdPost(idPost).get(i));
        }
         return new ResponseEntity<>(userApplyService.listUserApplyByIdPost(idPost),HttpStatus.OK);
    }
//t??m ra d???i t?????ng apply theo id
    @GetMapping("/userApplyById/{id}")
    public ResponseEntity<UserApply> getUserApplyById(@PathVariable int id){
        return new ResponseEntity<>(userApplyService.findById(id),HttpStatus.OK);
    }


//    x??a ??i nh???ng b??i post khi h???t h???n !
    @GetMapping("/deletePostExpired")
    public ResponseEntity<Boolean> deletePostExpired(){
          postEnterpriseService.deletePostExpired();
          return new ResponseEntity<>(HttpStatus.OK);
    }

}
