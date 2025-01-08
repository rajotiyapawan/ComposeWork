package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorLight
import com.rajotiya.mytestapp.loyalty.LoyaltyLandingActivity
import com.rajotiya.mytestapp.loyalty.LoyaltyUserEvents
import com.rajotiya.mytestapp.loyalty.LoyaltyViewModel
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTransactionItem
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTransactions
import com.rajotiya.mytestapp.utility.Constants
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Created by Pawan Rajotiya on 08-01-2025.
 */

@Composable
fun LoyaltyPointsLedgerView(
    modifier: Modifier,
    viewModel: LoyaltyViewModel = LoyaltyLandingActivity.localViewModelCompositionLocal.current
) {
    val data = viewModel.getDummyLedgerResponse()
    Column(modifier = modifier.background(color = Color.White)) {
        LoyaltyHeader(modifier = Modifier.fillMaxWidth(), points = data.balance ?: "") {
            viewModel.sendUserEvent(LoyaltyUserEvents.BackBtnClicked)
        }
        LazyColumn {
            item {
                PointsDetailView(
                    modifier = Modifier.fillMaxWidth(),
                    balance = data.balance ?: "",
                    earned = data.totalearned ?: "",
                    spent = data.totalspends ?: ""
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 20.dp, bottom = 16.dp)
                ) {
                    Text(
                        data.txns?.title ?: "",
                        fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
            }
            // transactions details month wise
            data.txns?.list?.let {
                items(it) { monthTransactions ->
                    TransactionDetailForMonth(modifier = Modifier.fillMaxWidth(), monthTransactions)
                }
            }
        }
    }
}

@Composable
private fun PointsDetailView(modifier: Modifier = Modifier, balance: String, earned: String, spent: String) {
    Column(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(colors = listOf(Color(0x19353535), Color.Black))
            )
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(balance, fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD), fontSize = 20.sp, color = Color.White)
        }
        Text("Available Points", fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD), fontSize = 12.sp, color = Color.White)
        Spacer(modifier = Modifier.height(18.dp))
        Row {
            Column {
                Text(
                    earned,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    fontSize = 12.sp,
                    color = Color.White
                )
                Text(
                    "Lifetime Earnings",
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
            Spacer(Modifier.width(30.dp))
            Column {
                Text(
                    spent,
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    fontSize = 12.sp,
                    color = Color.White
                )
                Text(
                    "Lifetime Spends",
                    fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun TransactionDetailForMonth(modifier: Modifier = Modifier, monthTransactions: LoyaltyTransactions) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xfff5f5f5))
                .padding(start = 20.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Text(
                monthTransactions.title ?: "",
                fontSize = 12.sp,
                color = textColorLight,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
        }

        monthTransactions.list?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 1000.dp)
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(it) { transaction ->
                    TransactionItem(modifier = Modifier.fillMaxWidth(), transaction)
                }
            }
        }
    }
}

@Composable
private fun TransactionItem(modifier: Modifier = Modifier, transaction: LoyaltyTransactionItem) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color(0xfff5f5f5), shape = CircleShape)
        ) {
            Image(painter = painterResource(R.drawable.ic_prime_bitmap_chat), contentDescription = null)
        }
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                transaction.title ?: "",
                fontSize = 12.sp,
                color = textColorDark,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
            Text(
                transaction.date ?: "",
                fontSize = 10.sp,
                color = textColorLight,
                fontFamily = getFontFamily(Constants.MONTSERRAT_REGULAR)
            )
        }
        Spacer(Modifier.weight(1f))
        val earned = transaction.txntype?.equals("earned") == true
        val amount = if (earned) "+ ${transaction.points}" else "- ${transaction.points}"
        Text(
            amount,
            fontFamily = getFontFamily(Constants.MONTSERRAT_SEMIBOLD),
            fontSize = 14.sp,
            color = if (earned) Color(0xff009681) else mbRed
        )
        Spacer(Modifier.width(5.dp))
        Image(painter = painterResource(R.drawable.loyalty_coin), contentDescription = null, modifier = Modifier.size(16.dp))
    }
}