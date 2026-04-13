package org.ycc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ycc.pojo.Dept;
import org.ycc.pojo.Result;
import org.ycc.service.DeptService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
//        System.out.println("查询全部的部门数据");
        log.info("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
//    方法一：通过原始的HttpServletRequest来接收参数
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest Request) {
//        String idStr = Request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据ID删除的部门"+ id);
//       return Result.success();
//    }

    //方法二：通过string提供的@requestParam注解，将请求方式绑定给方法形参

    /**
     * 注意事项：一旦声明@RequestParam，该参数在请求时必须传递，如果传递将会报错（默认required = true）
     *
     * @param
     * @return
     */
//    @DeleteMapping("/depts")
//    public Result delete( @RequestParam(value = "id",required = false) Integer deptid) {
//        System.out.println("根据ID删除的部门"+ deptid);
//       return Result.success();
//    }

    //方法三：如果前端请求参数名与形参变量名相同，则可以省略@RequestParam，直接定义方法形参接受即可
    //推荐！！！
    @DeleteMapping
    public Result delete(Integer id) {
//        System.out.println("根据ID删除的部门" + id);
        log.info("根据ID删除的部:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
//        System.out.println("新增部门" + dept);
        log.info("新增部门:{}",dept);
        deptService.addDept(dept);
        return Result.success();

    }

    /**
     * 根据Id查询部门
     */
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptid) {
//        System.out.println("根据id查询部门："+ deptid);
//
//        return Result.success();
//    }

    //如果路径参数的名称与方法形参名称一致 则可以省略@PathVariable括号里面的内容
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
//        System.out.println("根据id查询部门：" + id);
        log.info("根据id查询部门:{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     * @param dept
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门:"+ dept);
        log.info("修改部门:{}",dept);
        deptService.update(dept);
        return  Result.success();

    }

}
