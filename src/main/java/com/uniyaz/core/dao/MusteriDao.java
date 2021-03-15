package com.uniyaz.core.dao;

import com.uniyaz.core.domain.Musteri;
import com.uniyaz.core.dto.MusteriDto;
import com.uniyaz.core.dto.MusteriDtoNative;
import com.uniyaz.core.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.List;

public class MusteriDao {

    public void saveMusteri(Musteri musteri) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(musteri);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Musteri> findAByIdCriteria(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Musteri.class);
            criteria.add(Restrictions.eq("id", id));
            //criteria.add(Restrictions.like("kodu", "U", MatchMode.START));
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Musteri> findAllHql() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     musteriAlias " +
                            "From       Musteri musteriAlias ";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MusteriDto> findAllHqlAliasToBean() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     musteriAlias.id, musteriAlias.adi " +
                            "From       Musteri musteriAlias ";
            Query query = session.createQuery(hql);
            query.setResultTransformer(Transformers.aliasToBean(MusteriDto.class));
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MusteriDtoNative> findAllNative() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM MUSTERI");
            Query query = sqlQuery.setResultTransformer(Transformers.aliasToBean(MusteriDtoNative.class));
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
