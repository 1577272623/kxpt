package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.base.BaseController;
import com.rongyungov.kxpt.entity.Sysmaterial;
import com.rongyungov.kxpt.service.SysmaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * code is far away from bug with the animal protecting
 *
 * @author Cai
 * @description : Sysmaterial 控制器
 * ---------------------------------
 * @since 2020-08-04
 */
@RestController
@Api(value = "/sysmaterial", description = "Sysmaterial 控制器")
@RequestMapping("/sysmaterial")
public class SysmaterialController extends BaseController<SysmaterialService, Sysmaterial> {

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : Cai
     * @since : Create in 2020-08-04
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Sysmaterial> getSysmaterialList(@ApiParam(name = "sysmaterial", value = "筛选条件") @RequestBody(required = false) Sysmaterial sysmaterial,
                                                 @ApiParam(name = "pageIndex", value = "页数", required = true, defaultValue = "1") @RequestParam Integer pageIndex,
                                                 @ApiParam(name = "pageSize", value = "页大小", required = true, defaultValue = "10") @RequestParam Integer pageSize
    ) {
        Page<Sysmaterial> page = new Page<Sysmaterial>(pageIndex, pageSize);
        QueryWrapper<Sysmaterial> queryWrapper = new QueryWrapper<>(sysmaterial);
        return service.page(page, queryWrapper);
    }

    /**
     * @description : 通过id获取Sysmaterial
     * ---------------------------------
     * @author : Cai
     * @since : Create in 2020-08-04
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Sysmaterial")
    public Sysmaterial getSysmaterialById(@PathVariable Long id) {
        Sysmaterial sysmaterial = service.getById(id);
        return sysmaterial;
    }

    /**
     * @description : 通过id删除Sysmaterial
     * ---------------------------------
     * @author : Cai
     * @since : Create in 2020-08-04
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Sysmaterial")
    public Boolean delete(@PathVariable Long id) {
        Boolean success = service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Sysmaterial
     * ---------------------------------
     * @author : Cai
     * @since : Create in 2020-08-04
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Sysmaterials")
    public Boolean delete(@RequestBody(required = false) List<Sysmaterial> selectsysmaterials) {
        Boolean success = false;
        if (selectsysmaterials != null && selectsysmaterials.size() != 0) {
            List<Long> ids = new ArrayList<>();
            for (Sysmaterial sysmaterial : selectsysmaterials)
                ids.add(sysmaterial.getId());
            success = service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Sysmaterial
     * ---------------------------------
     * @author : Cai
     * @since : Create in 2020-08-04
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value = "通过id更新Sysmaterial")
    public Boolean update(@ApiParam(name = "id", value = "id主键值", required = true) @PathVariable Long id, @RequestBody Sysmaterial sysmaterial) {
        if (id != null)
            sysmaterial.setId(id);
        Boolean success = service.updateById(sysmaterial);
        return success;
    }

    /**
     * @description : 添加Sysmaterial
     * ---------------------------------
     * @author : Cai
     * @since : Create in 2020-08-04
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加Sysmaterial")
    public Boolean add(@RequestBody Sysmaterial sysmaterial) {
        Boolean success = service.save(sysmaterial);
        return success;
    }

}
