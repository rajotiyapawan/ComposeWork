package com.rajotiya.mytestapp.loyalty.usecase

import com.rajotiya.mytestapp.loyalty.models.FaqItem
import com.rajotiya.mytestapp.loyalty.models.InsufficientData
import com.rajotiya.mytestapp.loyalty.models.LoyaltyFaq
import com.rajotiya.mytestapp.loyalty.models.LoyaltyLandingPageData
import com.rajotiya.mytestapp.loyalty.models.LoyaltySections
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTaskBenefitItem
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTaskBenefits
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTaskItem
import com.rajotiya.mytestapp.loyalty.models.LoyaltyTasks
import com.rajotiya.mytestapp.loyalty.models.MileStone
import com.rajotiya.mytestapp.loyalty.models.Placeholder
import com.rajotiya.mytestapp.loyalty.models.RewardDetailData
import com.rajotiya.mytestapp.loyalty.models.RewardItem
import com.rajotiya.mytestapp.loyalty.models.Rewards
import com.rajotiya.mytestapp.loyalty.models.TextModel
import com.rajotiya.mytestapp.utility.MBCoreResultEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Pawan Rajotiya on 03-01-2025.
 */
class LoyaltyRepoImpl : LoyaltyRepo {
    override suspend fun getLandingPageData(): Flow<MBCoreResultEvent<LoyaltyLandingPageData>> =
        flow {
            emit(MBCoreResultEvent.OnLoading)

            delay(500)

            emit(MBCoreResultEvent.OnSuccess(getDummyLandingData()))
        }

    override suspend fun getRewardsDetail(params: LoyaltyUseCase.RewardDetailParams): Flow<MBCoreResultEvent<RewardDetailData>> =
        flow {
            emit(MBCoreResultEvent.OnLoading)

            delay(500)

            emit(MBCoreResultEvent.OnSuccess(getDummyResponse()))
        }

    private fun getDummyLandingData(): LoyaltyLandingPageData {
        return LoyaltyLandingPageData(
            status = "1",
            title = "MB Elite Club",
            titleUrl = "",
            subtitle = "Exclusive Offers, Rewards & more..",
            pnts = "15000",
            pnturl = "",
            tabs = listOf("What’s Elite Club?", "My Rewards", "How to Claim"),
            rwdurl = null, bgurl = null, vidId = null, vidurl = null,
            rwdsProgress = listOf(
                MileStone(imgUrl = null, pnts = "", pntsD = "0k", claimed = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "1k", claimed = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "2k"),
                MileStone(imgUrl = null, pnts = "", pntsD = "10k", locked = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "12k", locked = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "15k", locked = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "16k", locked = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "17k", locked = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "18k", locked = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "19k", locked = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "20k", locked = "y"),
                MileStone(imgUrl = null, pnts = "", pntsD = "30k", locked = "y"),
            ),
            rewards = Rewards(
                title = "Exclusive Rewards", gfturl = null, items = listOf(
                    RewardItem(imgUrl = "", title = "MB Prime Membership", worth = "200", points = "2000"),
                    RewardItem(imgUrl = "", title = "Apple iPhone 16 Plus", worth = "200", points = "2000", locked = "y"),
                    RewardItem(
                        imgUrl = "",
                        title = "₹1,000 off on movie tickets",
                        worth = "1000",
                        points = "2000",
                        claimed = "y"
                    ),
                    RewardItem(imgUrl = "", title = "MB Prime Membership", worth = "200", points = "2000"),
                    RewardItem(imgUrl = "", title = "MB Prime Membership", worth = "200", points = "2000"),
                )
            ), help = null, faq = LoyaltyFaq(
                title = "FAQ's", items = listOf(
                    FaqItem(
                        que = TextModel(
                            text = "How Can I earn more points?",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "14"
                        ),
                        ans = TextModel(
                            text = "Do the tasks and you will gain points on completion of every task",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12"
                        )
                    ),
                    FaqItem(
                        que = TextModel(
                            text = "How can I redeem rewards?",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "14"
                        ),
                        ans = TextModel(
                            text = "Use reward points", color = "#303030", weight = "Regular", font = "Montserrat", size = "12"
                        )
                    ),
                    FaqItem(
                        que = TextModel(
                            text = "Terms and conditions",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "14"
                        ),
                        ans = TextModel(
                            text = "Do the tasks and you will gain points on complition of every task",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12"
                        )
                    ),
                    FaqItem(
                        que = TextModel(
                            text = "How Can I earn more points?",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "14"
                        ),
                        ans = TextModel(
                            text = "Do the tasks and you will gain points on complition of every task",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12"
                        )
                    ),
                    FaqItem(
                        que = TextModel(
                            text = "How Can I earn more points?",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "14"
                        ),
                        ans = TextModel(
                            text = "Do the tasks and you will gain points on complition of every task",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12"
                        )
                    ),
                )
            )
        )
    }

    private fun getDummyResponse(): RewardDetailData {
        return RewardDetailData(
            status = "1",
            title = "Annual Subscription of Times Prime",
            titleUrl = "",
            earnedPoints = "23433",
            worth = "1299",
            points = "10000",
            pnturl = "",
            insufficient = "y",
            insufficientPntData = InsufficientData(
                title = "Insufficient Points", pointsToEarn = "3,000"
            ),
            tasks = LoyaltyTasks(
                title = "How to earn more points?",
                items = listOf(
                    LoyaltyTaskItem(
                        title = TextModel(
                            text = "Go On Property Site Visits with Magicbricks",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet",
                            placeholder = listOf(
                                Placeholder(text = "with Magicbricks", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )
                        ),
                        type = "sitevisit",
                        points = "50000",
                        benefits = LoyaltyTaskBenefits(
                            title = "You Get",
                            list = listOf(
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Free Cab Pickup & Drop Service",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "freecab"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Dedicated Relationship manager",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "rm"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Super Badge",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "superbadge")
                            )
                        ),
                        cta = "Shortlist Projects"),
                    LoyaltyTaskItem(
                        title = TextModel(
                            text = "Go On Property Site Visits with Magicbricks",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet",
                            placeholder = listOf(
                                Placeholder(text = "with Magicbricks", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )
                        ),
                        type = "sitevisit",
                        points = "50000",
                        benefits = LoyaltyTaskBenefits(
                            title = "You Get",
                            list = listOf(
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Free Cab Pickup & Drop Service",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "freecab"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Dedicated Relationship manager",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "rm"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Super Badge",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "superbadge")
                            )
                        ),
                        cta = "Shortlist Projects"),
                    LoyaltyTaskItem(
                        title = TextModel(
                            text = "Go On Property Site Visits with Magicbricks",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet",
                            placeholder = listOf(
                                Placeholder(text = "with Magicbricks", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )
                        ),
                        type = "sitevisit",
                        points = "50000",
                        benefits = LoyaltyTaskBenefits(
                            title = "You Get",
                            list = listOf(
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Free Cab Pickup & Drop Service",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "freecab"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Dedicated Relationship manager",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "rm"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Super Badge",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "superbadge")
                            )
                        ),
                        cta = "Shortlist Projects"),
                    LoyaltyTaskItem(
                        title = TextModel(
                            text = "Go On Property Site Visits with Magicbricks",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet",
                            placeholder = listOf(
                                Placeholder(text = "with Magicbricks", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )
                        ),
                        type = "sitevisit",
                        points = "50000",
                        benefits = LoyaltyTaskBenefits(
                            title = "You Get",
                            list = listOf(
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Free Cab Pickup & Drop Service",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "freecab"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Dedicated Relationship manager",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "rm"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Super Badge",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "superbadge")
                            )
                        ),
                        cta = "Shortlist Projects"),
                    LoyaltyTaskItem(
                        title = TextModel(
                            text = "Go On Property Site Visits with Magicbricks",
                            color = "#303030",
                            weight = "SemiBold",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet",
                            placeholder = listOf(
                                Placeholder(text = "with Magicbricks", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )
                        ),
                        type = "sitevisit",
                        points = "50000",
                        benefits = LoyaltyTaskBenefits(
                            title = "You Get",
                            list = listOf(
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Free Cab Pickup & Drop Service",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "freecab"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Dedicated Relationship manager",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "rm"),
                                LoyaltyTaskBenefitItem(text = TextModel(
                                    text = "Super Badge",
                                    color = "#303030",
                                    weight = "Regular",
                                    font = "Montserrat",
                                    size = "12",
                                ), imgUrl = "", type = "superbadge")
                            )
                        ),
                        cta = "Shortlist Projects"),
                ),
            ),
            items = listOf(
                LoyaltySections(
                    text = "About this Reward",
                    type = "aboutrwds",
                    color = "#303030",
                    weight = "SemiBold",
                    font = "Montserrat",
                    size = "18",
                    bgcolor = "#fff5cc",
                    items = listOf(
                        TextModel(
                            text = "Get a BookMyshow Gift Voucher worth 500",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet",
                            placeholder = listOf(
                                Placeholder(text = "500", color = "#303030", weight = "Regular", font = "Montserrat", size = "12")
                            )
                        ),
                        TextModel(
                            text = "Reward can be claimed only once per user.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet"
                        ),
                        TextModel(
                            text = "This reward cannot be sold, canceled, returned, refunded, or exchanged once claimed.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet"
                        )
                    )
                ),
                LoyaltySections(
                    text = "How to use",
                    type = "howtouserwds",
                    color = "#303030",
                    weight = "SemiBold",
                    font = "Montserrat",
                    size = "18",
                    bgcolor = "#f5f5f5",
                    subtext = listOf(
                        TextModel(
                            text = "After you redeem this reward:",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12"
                        )
                    ),
                    items = listOf(
                        TextModel(
                            text = "We''ll email your Gift Voucher to you.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet"
                        ),
                        TextModel(
                            text = "Log in to your BookMyShow account.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet"
                        ),
                        TextModel(
                            text = "Choose the tickets you want to buy.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet"
                        ),
                        TextModel(
                            text = "Go to the payment options and select the Gift Voucher payment method.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet"
                        ),
                        TextModel(
                            text = "Enter the 16-digit gift card number in the Pay using gift voucher field.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet"
                        ),
                        TextModel(
                            text = "Click Apply to buy tickets.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                            itemType = "bullet"
                        )
                    )
                ),
                LoyaltySections(
                    text = "Frequently asked questions",
                    type = "qnarewrds",
                    color = "#303030",
                    weight = "SemiBold",
                    font = "Montserrat",
                    size = "18",
                    bgcolor = "#fff5cc",
                    qna = listOf(
                        FaqItem(
                            que = TextModel(
                                text = "How Can I earn more points?",
                                color = "#303030",
                                weight = "SemiBold",
                                font = "Montserrat",
                                size = "14"
                            ),
                            ans = TextModel(
                                text = "Do the tasks and you will gain points on completion of every task",
                                color = "#303030",
                                weight = "Regular",
                                font = "Montserrat",
                                size = "12"
                            )
                        ),
                        FaqItem(
                            que = TextModel(
                                text = "How can I redeem rewards?",
                                color = "#303030",
                                weight = "SemiBold",
                                font = "Montserrat",
                                size = "14"
                            ),
                            ans = TextModel(
                                text = "Use reward points",
                                color = "#303030",
                                weight = "Regular",
                                font = "Montserrat",
                                size = "12"
                            )
                        ),
                        FaqItem(
                            que = TextModel(
                                text = "Terms and conditions",
                                color = "#303030",
                                weight = "SemiBold",
                                font = "Montserrat",
                                size = "14"
                            ),
                            ans = TextModel(
                                text = "Do the tasks and you will gain points on complition of every task",
                                color = "#303030",
                                weight = "Regular",
                                font = "Montserrat",
                                size = "12"
                            )
                        ),
                        FaqItem(
                            que = TextModel(
                                text = "How Can I earn more points?",
                                color = "#303030",
                                weight = "SemiBold",
                                font = "Montserrat",
                                size = "14"
                            ),
                            ans = TextModel(
                                text = "Do the tasks and you will gain points on complition of every task",
                                color = "#303030",
                                weight = "Regular",
                                font = "Montserrat",
                                size = "12"
                            )
                        ),
                        FaqItem(
                            que = TextModel(
                                text = "How Can I earn more points?",
                                color = "#303030",
                                weight = "SemiBold",
                                font = "Montserrat",
                                size = "14"
                            ),
                            ans = TextModel(
                                text = "Do the tasks and you will gain points on complition of every task",
                                color = "#303030",
                                weight = "Regular",
                                font = "Montserrat",
                                size = "12"
                            )
                        ),
                    )
                ),
                LoyaltySections(
                    text = "Terms and conditions",
                    type = "tncrewrds",
                    color = "#303030",
                    weight = "SemiBold",
                    font = "Montserrat",
                    size = "18",
                    bgcolor = "#fff5cc",
                    items = listOf(
                        TextModel(
                            text = "Horem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu turpis molestie, dictum est a, mattis tellus. Sed dignissim, metus nec fringilla accumsan, risus sem sollicitudin lacus, ut interdum tellus elit sed risus. Maecenas eget condimentum velit, sit amet feugiat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent auctor purus luctus enim egestas, ac scelerisque ante pulvinar. Donec ut rhoncus ex. Suspendisse ac rhoncus nisl, eu tempor urna. Curabitur vel bibendum lorem. Morbi convallis convallis diam sit amet lacinia. Aliquam in elementum tellus.",
                            color = "#303030",
                            weight = "Regular",
                            font = "Montserrat",
                            size = "12",
                        )
                    )
                ),
            )
        )
    }
}