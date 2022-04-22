package net.maku.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.Result;
import net.maku.system.convert.SysOrgConvert;
import net.maku.system.entity.SysOrgEntity;
import net.maku.system.service.SysOrgService;
import net.maku.system.vo.org.SysOrgPostVO;
import net.maku.system.vo.org.SysOrgPutVO;
import net.maku.system.vo.org.SysOrgVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 机构管理
 * 
 * @author 阿沐 babamu@126.com
 */
@RestController
@RequestMapping("sys/org")
@Tag(name="机构管理")
@AllArgsConstructor
public class SysOrgController {
	private final SysOrgService sysOrgService;

	@GetMapping("list")
	@Operation(summary = "列表")
	//@PreAuthorize("hasAuthority('sys:org:list')")
	public Result<List<SysOrgVO>> list(){
		List<SysOrgVO> list = sysOrgService.getList();

		return Result.ok(list);
	}

	@GetMapping("{id}")
	@Operation(summary = "信息")
	//@PreAuthorize("hasAuthority('sys:org:info')")
	public Result<SysOrgVO> get(@PathVariable("id") Long id){
		SysOrgEntity entity = sysOrgService.getById(id);

		return Result.ok(SysOrgConvert.INSTANCE.convert(entity));
	}

	@PostMapping
	@Operation(summary = "保存")
	//@PreAuthorize("hasAuthority('sys:org:save')")
	public Result<String> save(@RequestBody @Valid SysOrgPostVO vo){
		sysOrgService.save(vo);

		return Result.ok();
	}

	@PutMapping
	@Operation(summary = "修改")
	//@PreAuthorize("hasAuthority('sys:org:update')")
	public Result<String> update(@RequestBody @Valid SysOrgPutVO vo){
		sysOrgService.update(vo);

		return Result.ok();
	}

	@DeleteMapping("{id}")
	@Operation(summary = "删除")
	//@PreAuthorize("hasAuthority('sys:org:delete')")
	public Result<String> delete(@PathVariable("id") Long id){
		sysOrgService.delete(id);

		return Result.ok();
	}
	
}