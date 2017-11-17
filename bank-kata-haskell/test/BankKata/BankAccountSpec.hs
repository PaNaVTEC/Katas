module BankKata.BankAccountSpec (main, spec) where

import           Test.Hspec
import           Test.QuickCheck

import           BankKata.BankAccount
import           BankKata.Natural
import           Data.Time

main :: IO ()
main = hspec spec

anAmount :: Maybe Nat
anAmount = natural 1

aDate :: Day
aDate = fromGregorian 2017 10 11

firstTransaction :: BankAccount -> Transaction
firstTransaction = head . getTransactions

withdraw' :: Day -> Maybe Nat -> Maybe BankAccount -> Maybe BankAccount
withdraw' date amount account = withdraw date <$> amount <*> account

deposit' :: Day -> Maybe Nat -> Maybe BankAccount -> Maybe BankAccount
deposit' date amount account = deposit date <$> amount <*> account

spec :: Spec
spec = do
  describe "BankAccount" $ do
    it "Deposits an amount to a new bankAccount" $ do
      firstTransaction <$> (deposit' aDate anAmount (Just emptyAccount))

       `shouldBe`

        Deposit aDate <$> anAmount

    it "Withdraws an amount twice" $ do
      getTransactions <$> (
        (withdraw' aDate anAmount) . (withdraw' aDate anAmount) $ (Just emptyAccount))

       `shouldBe`

        (sequence $ replicate 2 (Withdraw aDate <$> anAmount))

    it "Prints an empty statement" $ do
      printStatement <$> (
        (withdraw' aDate $ natural 100)
        . (deposit' aDate $ natural 10)
        . (deposit' aDate $ natural 1000) $ (Just emptyAccount))

        `shouldBe`

        Just
        "  date | credit | debit | balance \n\
        \ 11-10 |        |   100 |     910 \n\
        \ 11-10 |     10 |       |    1010 \n\
        \ 11-10 |   1000 |       |    1000 "
