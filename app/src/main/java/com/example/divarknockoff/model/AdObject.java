package com.example.divarknockoff.model;

import com.example.divarknockoff.greendao.UUIDConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "Advert")
public class AdObject {
    @Id(autoincrement = true)
    private Long _id;

    @Property(nameInDb = "uuid")
    @Index(unique = true)
    @Convert(converter = UUIDConverter.class, columnType = String.class)
    private UUID id;

    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "content")
    private String content;

    @Property(nameInDb = "time")
    private String time;

    @Property(nameInDb = "city")
    private String city;

    @Property(nameInDb = "vip")
    private boolean vip;

    @Property(nameInDb = "phone")
    private String phone;

    @Property(nameInDb = "category")
    private String category;

    private Long accountId;

    @ToOne(joinProperty = "accountId")
    private Account account;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 372769464)
    private transient AdObjectDao myDao;

    public String getPhotoName() {
        return "IMG_" + getId() + ".jpg";
    }


    @Generated(hash = 1686669698)
    public AdObject(Long _id, UUID id, String title, String content, String time,
            String city, boolean vip, String phone, String category,
            Long accountId) {
        this._id = _id;
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.city = city;
        this.vip = vip;
        this.phone = phone;
        this.category = category;
        this.accountId = accountId;
    }

    @Generated(hash = 1039172398)
    public AdObject() {
    }

    @Generated(hash = 1501133588)
    private transient Long account__resolvedKey;



    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getVip() {
        return this.vip;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 531730087)
    public Account getAccount() {
        Long __key = this.accountId;
        if (account__resolvedKey == null || !account__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AccountDao targetDao = daoSession.getAccountDao();
            Account accountNew = targetDao.load(__key);
            synchronized (this) {
                account = accountNew;
                account__resolvedKey = __key;
            }
        }
        return account;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1910176546)
    public void setAccount(Account account) {
        synchronized (this) {
            this.account = account;
            accountId = account == null ? null : account.getId();
            account__resolvedKey = accountId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 941563632)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAdObjectDao() : null;
    }

    public static final class AdBuilder {
        private Long _id;
        private UUID id;
        private String title;
        private String content;
        private String time;
        private String city;
        private boolean vip;
        private String phone;
        private String category;
        private UUID userId;

        private AdBuilder() {
        }

        public static AdBuilder anAdObject() {
            return new AdBuilder();
        }

        public AdBuilder _id(Long _id) {
            this._id = _id;
            return this;
        }

        public AdBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public AdBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AdBuilder content(String content) {
            this.content = content;
            return this;
        }

        public AdBuilder time(String time) {
            this.time = time;
            return this;
        }

        public AdBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AdBuilder vip(boolean vip) {
            this.vip = vip;
            return this;
        }

        public AdBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public AdBuilder category(String category) {
            this.category = category;
            return this;
        }

        public AdBuilder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public AdObject build() {
            AdObject adObject = new AdObject();
            adObject.set_id(_id);
            adObject.setTitle(title);
            adObject.setContent(content);
            adObject.setTime(time);
            adObject.setCity(city);
            adObject.setVip(vip);
            adObject.setPhone(phone);
            adObject.setCategory(category);
            adObject.id = this.id;
            return adObject;
        }
    }

}
