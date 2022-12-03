package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TogMessageMapper;
import com.ruoyi.system.domain.TogMessage;
import com.ruoyi.system.service.ITogMessageService;

/**
 * 消息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
@Service
public class TogMessageServiceImpl implements ITogMessageService 
{
    @Autowired
    private TogMessageMapper togMessageMapper;

    /**
     * 查询消息
     * 
     * @param id 消息主键
     * @return 消息
     */
    @Override
    public TogMessage selectTogMessageById(Long id)
    {
        return togMessageMapper.selectTogMessageById(id);
    }

    /**
     * 查询消息列表
     * 
     * @param togMessage 消息
     * @return 消息
     */
    @Override
    public List<TogMessage> selectTogMessageList(TogMessage togMessage)
    {
        List<TogMessage> togMessages = togMessageMapper.selectTogMessageList(togMessage);
        for (TogMessage message : togMessages) {
            message.setStatus(1);
            updateTogMessage(message);
        }
        return togMessages;
    }

    void doView(List<Long> ids){

    }

    /**
     * 新增消息
     * 
     * @param togMessage 消息
     * @return 结果
     */
    @Override
    public int insertTogMessage(TogMessage togMessage)
    {
        togMessage.setCreateTime(DateUtils.getNowDate());
        return togMessageMapper.insertTogMessage(togMessage);
    }

    /**
     * 修改消息
     * 
     * @param togMessage 消息
     * @return 结果
     */
    @Override
    public int updateTogMessage(TogMessage togMessage)
    {
        togMessage.setUpdateTime(DateUtils.getNowDate());
        return togMessageMapper.updateTogMessage(togMessage);
    }

    /**
     * 批量删除消息
     * 
     * @param ids 需要删除的消息主键
     * @return 结果
     */
    @Override
    public int deleteTogMessageByIds(Long[] ids)
    {
        return togMessageMapper.deleteTogMessageByIds(ids);
    }

    /**
     * 删除消息信息
     * 
     * @param id 消息主键
     * @return 结果
     */
    @Override
    public int deleteTogMessageById(Long id)
    {
        return togMessageMapper.deleteTogMessageById(id);
    }
}
