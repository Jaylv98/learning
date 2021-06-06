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
public class AccountServiceImpl_old implements AccountService {

    private AccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(AccountDao accountDao) {

        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        try{
            //开启事务
            txManager.beginTransaction();
            //执行操作
             List<Account> accounts=accountDao.findAllAccount();
            //提交事务
            txManager.commit();
            //返回结果
            return accounts;
        }catch (Exception e){
            //回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            txManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try{
            txManager.beginTransaction();
            Account account=accountDao.findAccountById(accountId);
            txManager.commit();
            return account;
        }catch (Exception e){
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            txManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try{
            txManager.beginTransaction();
            accountDao.saveAccount(account);
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally {
            txManager.release();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            txManager.beginTransaction();
            accountDao.updateAccount(account);
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally {
            txManager.release();
        }
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        try{
            txManager.beginTransaction();
            accountDao.deleteAccount(acccountId);
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally {
            txManager.release();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {

        try{
            txManager.beginTransaction();

            Account source=accountDao.findAccountByName(sourceName);//根据名称查询转出账户

            Account target=accountDao.findAccountByName(targetName); //根据名称查询转入账户

            source.setMoney(source.getMoney()-money);//转出账户减钱

            int a=1/0;

            target.setMoney(target.getMoney()+money);//转入账户加钱

            accountDao.updateAccount(source); //更新转出账户

            accountDao.updateAccount(target);//更新转入账户
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
            e.printStackTrace();
        }finally {
            txManager.release();
        }


    }
}
