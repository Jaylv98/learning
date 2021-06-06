package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import com.itheima.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 *  事务控制应该都在业务层
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;


    public void setAccountDao(AccountDao accountDao) {

        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
       return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
      return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
       accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
       accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
       accountDao.deleteAccount(acccountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        try{
            //根据名称查询转出账户
            Account source=accountDao.findAccountByName(sourceName);
            //根据名称查询转入账户
            Account target=accountDao.findAccountByName(targetName);
            //转出账户减钱
            source.setMoney(source.getMoney()-money);
            //转入账户加钱
            target.setMoney(target.getMoney()+money);
            //更新转出账户
            accountDao.updateAccount(source);

            //int i=1/0;

            //更新转入账户
            accountDao.updateAccount(target);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
