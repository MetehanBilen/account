package com.bank.account.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Account(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name  = "UUID", strategy =  "org.hibernate.id.UUIDGenerator")
    val id:String? = "",
    val balance: BigDecimal? = BigDecimal.ZERO,
    val creationDate: LocalDateTime? = null,

    @ManyToOne(fetch =  FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id", nullable = false)
    val customer : Customer? = null,

    //Fetch ve cascade nedir araştır.
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val transaction : Set<Transaction> =HashSet()
    //"account" değişken adı ve Transaction.kt'deki ile aynı olmalı.
    // eğer mappedBy = "account_degeri" olsaydı Transaction.kt'ye bak
)

{
constructor( customer: Customer,balance: BigDecimal,creationDate: LocalDateTime)
        : this("",
            customer = customer,
            balance = balance,
            creationDate = creationDate,
        )
    //sağ tıkla generate hashcode next next
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (id != other.id) return false
        if (balance != other.balance) return false
        if (creationDate != other.creationDate) return false
        if (customer != other.customer) return false
        if (transaction != other.transaction) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (balance?.hashCode() ?: 0)
        result = 31 * result + creationDate.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
        return result

        // OneToMany olanları sil.
    }
}


