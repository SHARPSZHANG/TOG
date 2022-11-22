package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TogMessage;

/**
 * 消息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public interface TogMessageMapper 
{
    /**
     * 查询消息
     * 
     * @param id 消息主键
     * @return 消息
     */
    public TogMessage selectTogMessageById(Long id);

    /**
     * 查询消息列表
     * 
     * @param togMessage 消息
     * @return 消息集合
     */
    public List<TogMessage> selectTogMessageList(TogMessage togMessage);

    /**
     * 新增消息
     * 
     * @param togMessage 消息
     * @return 结果
     */
    public int insertTogMessage(TogMessage togMessage);

    /**
     * 修改消息
     * 
     * @param togMessage 消息
     * @return 结果
     */
    public int updateTogMessage(TogMessage togMessage);

    /**
     * 删除消息
     * 
     * @param id 消息主键
     * @return 结果
     */
    public int deleteTogMessageById(Long id);

    /**
     * 批量删除消息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTogMessageByIds(Long[] ids);
}
