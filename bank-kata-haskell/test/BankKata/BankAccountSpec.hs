module BankKata.BankAccountSpec (main, spec) where

import           Test.Hspec
import           Test.QuickCheck

import           BankKata.BankAccount
import           BankKata.Natural

main :: IO ()
main = hspec spec

anAmount = natural 1
firstTransaction = head . getTransactions

withdraw' :: Maybe Nat -> Maybe BankAccount -> Maybe BankAccount
withdraw' amount account = withdraw <$> amount <*> account

deposit' :: Maybe Nat -> Maybe BankAccount -> Maybe BankAccount
deposit' amount account = deposit <$> amount <*> account

spec :: Spec
spec = do
  describe "BankAccount" $ do
    it "Deposits an amount to a new bankAccount" $ do
      firstTransaction <$> (deposit' anAmount (Just emptyAccount))

       `shouldBe`

        Deposit <$> anAmount

    it "Withdraws an amount twice" $ do
      getTransactions <$> (
        (withdraw' anAmount) . (withdraw' anAmount) $ (Just emptyAccount))

       `shouldBe`

        (sequence $ replicate 2 (Withdraw <$> anAmount))

    it "Prints an empty statement" $ do
      printStatement <$> (
        (withdraw' $ natural 100)
        . (deposit' $ natural 10)
        . (deposit' $ natural 1000) $ (Just emptyAccount))

        `shouldBe`

        Just
        " date | credit | debit | balance \n\
        \      |        |   100 |     910 \n\
        \      |     10 |       |    1010 \n\
        \      |   1000 |       |    1000 "
