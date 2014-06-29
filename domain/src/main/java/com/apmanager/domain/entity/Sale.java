package com.apmanager.domain.entity;

import com.apmanager.domain.base.Computer;
import com.apmanager.domain.base.BasicEntity;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "sale")
public class Sale implements BasicEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float total;

    @Column(name = "open_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date openDate;

    @Column(name = "close_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date closedDate;

    @Column(name = "canceled", nullable = false)
    private boolean canceled = false;

    @Column(name = "closed", nullable = false)
    private boolean closed = false;

    @OneToMany(mappedBy = "sale",
            cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SaleProduct> products;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closeDate) {
        this.closedDate = closeDate;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public List<SaleProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SaleProduct> products) {
        this.products = products;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public boolean isActive() {
        return true;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getDisplayName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getProductQuantity() {
        if (products == null) {
            return 0;
        }
        int localTotal = 0;
        for (SaleProduct p : products) {
            if (p.getQuantity() != null) {
                localTotal += p.getQuantity();
            }
        }
        return localTotal;
    }

    public boolean isEmpty() {
        return getProductQuantity() == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sale other = (Sale) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", total=" + total
                + ", openDate=" + openDate + ", closeDate=" + closedDate + '}';
    }
}
