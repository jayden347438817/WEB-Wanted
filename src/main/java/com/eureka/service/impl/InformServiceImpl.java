package com.eureka.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eureka.dao.InformDao;
import com.eureka.domain.Inform;
import com.eureka.service.InformService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InformServiceImpl extends ServiceImpl<InformDao, Inform> implements InformService {
    @Override
    public IPage<Inform> getPage(Integer page, Integer size) {
        return baseMapper.selectPage(new Page(page, size), null);
    }

    @Override
    public IPage<Inform> getPage(Integer page, Integer size, Inform inform) {
        LambdaQueryWrapper<Inform> queryWrapper = new LambdaQueryWrapper<>();
        if(inform.getId()!=null){
            queryWrapper.eq(Inform::getId,inform.getId());
        }
        queryWrapper.like(Strings.isNotEmpty(inform.getLeader()),Inform::getLeader,inform.getLeader());
        queryWrapper.like(Strings.isNotEmpty(inform.getWantedHead()),Inform::getWantedHead,inform.getWantedHead());
        queryWrapper.like(Strings.isNotEmpty(inform.getType()),Inform::getType,inform.getType());
        return baseMapper.selectPage(new Page(page, size),queryWrapper);
    }

    @Override
    public List<Inform> getByType(String type) {
        LambdaQueryWrapper<Inform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Inform::getType,type);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean join(Integer id, String username) {
        Inform inform = baseMapper.selectById(id);
        if(Objects.equals(username, inform.getLeader())){
            return false;
        }
        if(Objects.equals(username, inform.getMember2())){
            return false;
        }
        if(Objects.equals(username, inform.getMember3())){
            return false;
        }
        if(Objects.equals(username, inform.getMember4())){
            return false;
        }
        if(Objects.equals(username, inform.getMember5())){
            return false;
        }
        if(Objects.equals(username, inform.getMember6())){
            return false;
        }
        if(Objects.equals(username, inform.getMember7())){
            return false;
        }
        if(Objects.equals(username, inform.getMember8())){
            return false;
        }
        if(inform.getPersonCur() < inform.getPersonAll()){
            inform.setPersonCur(inform.getPersonCur()+1);
            switch (inform.getPersonCur()){
                case 2:
                    inform.setMember2(Integer.valueOf(username));
                    break;
                case 3:
                    inform.setMember3(Integer.valueOf(username));
                    break;
                case 4:
                    inform.setMember4(Integer.valueOf(username));
                    break;
                case 5:
                    inform.setMember5(Integer.valueOf(username));
                    break;
                case 6:
                    inform.setMember6(Integer.valueOf(username));
                    break;
                case 7:
                    inform.setMember7(Integer.valueOf(username));
                    break;
                case 8:
                    inform.setMember8(Integer.valueOf(username));
                    break;
            }
            return baseMapper.updateById(inform) > 0;
        }else{
            return false;
        }
    }

    @Override
    public List<Inform> getByLeader(String leader) {
        LambdaQueryWrapper<Inform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Inform::getLeader,leader);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Inform> getByMember(String member) {
        LambdaQueryWrapper<Inform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Inform::getMember2,member);
        queryWrapper.or();
        queryWrapper.eq(Inform::getMember3,member);
        queryWrapper.or();
        queryWrapper.eq(Inform::getMember4,member);
        queryWrapper.or();
        queryWrapper.eq(Inform::getMember5,member);
        queryWrapper.or();
        queryWrapper.eq(Inform::getMember6,member);
        queryWrapper.or();
        queryWrapper.eq(Inform::getMember7,member);
        queryWrapper.or();
        queryWrapper.eq(Inform::getMember8,member);
        return baseMapper.selectList(queryWrapper);
    }
}
