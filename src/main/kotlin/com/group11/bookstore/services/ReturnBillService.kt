package com.group11.bookstore.services

import com.group11.bookstore.models.*
import com.group11.bookstore.repositories.RentBillRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReturnBillService
@Autowired constructor(val rentBillRepository: RentBillRepository) {

    private fun findNeedReturnCollateral(returnBill: ReturnBill)
        : MutableList<RentCollateral> {

        val pendingBills =
            rentBillRepository.findPendingBills(returnBill.customer)

        val returnDetailIds = returnBill.rentBooks
            .map { returnDetail -> returnDetail.id }
            .toSet()

        return pendingBills
            .filter { bill ->
                bill.rentBooks
                    .filter { rentDetail -> rentDetail.isReturned() }
                    .all { rentDetail -> returnDetailIds.contains(rentDetail.id) }
            }
            .flatMap { bill -> bill.collaterals }
            .toMutableList()
    }

    private fun getReturnDetails(
        returnBill: ReturnBill,
        rentDetails: List<RentDetail>,
        rentPenalties: Map<RentDetail, List<Penalty>>
    ): MutableList<ReturnDetail> {

        val returnDetails = ArrayList<ReturnDetail>()

        for (rentDetail in rentDetails) {

            val returnDetail = ReturnDetail(
                bill = returnBill,
                rentDetail = rentDetail,
                penalties = ArrayList(),
                price = 0,
            )

            var penalties = rentPenalties[rentDetail]
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
        rentPenalties: Map<RentDetail, List<Penalty>>
    ): ReturnBill {

        val returnBill = ReturnBill(
            customer = customer,
            submitter = submitter,
            rentBooks = ArrayList(),
            collaterals = ArrayList(),
        )

        returnBill.rentBooks = getReturnDetails(
            returnBill,
            rentDetails,
            rentPenalties
        )

        returnBill.collaterals = findNeedReturnCollateral(returnBill)
            .map { collateral ->
                ReturnCollateral(
                    bill = returnBill,
                    collateral = collateral.collateral
                )
            }
            .toMutableList()

        return returnBill
    }
}
