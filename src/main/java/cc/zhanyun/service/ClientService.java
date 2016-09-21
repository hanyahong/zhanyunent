package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.vo.ClientVO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public abstract interface ClientService {
    public abstract Info addClientOne(Clientmanager paramClientmanager);

    public abstract Info addClientImage(MultipartFile paramMultipartFile, String paramString);

    public abstract Clientmanager selClientInfo(String paramString);

    public abstract Info delClientInfo(String paramString);

    public abstract List<ClientVO> selClientList(Integer num ,Integer size);

    public abstract Info updateClientOne(Clientmanager paramClientmanager);

    public abstract List<Info> batchClient(List<Clientmanager> clist);
}

