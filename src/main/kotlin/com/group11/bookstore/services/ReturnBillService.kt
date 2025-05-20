package com.group11.bookstore.services

import com.group11.bookstore.models.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReturnBillService
@Autowired constructor() {

    private fun getReturnDetails(
        returnBill: ReturnBill,
        rentDetails: List<RentDetail>,
        rentPenalties: Map<Book, List<Penalty>>
    ): MutableList<ReturnDetail> {

        val returnDetails = ArrayList<ReturnDetail>()

        for (rentDetail in rentDetails) {

            val returnDetail = ReturnDetail(
                bill = returnBill,
                rentDetail = rentDetail,
                penalties = ArrayList(),
                price = 0,
            )

            var penalties = rentPenalties[rentDetail.book]
            if (penalties == null) {
                penalties = listOf()
            }

            returnDetail.penalties = penalties
                .map { penalty ->
                    ReturnPenalty(
                        returnDetail = returnDetail,
                        penalty = penalty,
                        fee = penalty.fee,
                    )
                }
                .toMutableList()

            returnDetails.add(returnDetail)
        }

        return returnDetails
    }

    fun getBill(
        submitter: User,
        customer: Customer,
        rentDetails: List<RentDetail>,
        rentPenalties: Map<Book, List<Penalty>>
    ): ReturnBill? {

        val returnBill = ReturnBill(
            customer = customer,
            submitter = submitter,
            rentBooks = ArrayList(),
            collaterals = ArrayList(), // TODO: fill this
        )

        val returnDetails = getReturnDetails(
            returnBill,
            rentDetails,
            rentPenalties
        )

        return null;
    }
}
