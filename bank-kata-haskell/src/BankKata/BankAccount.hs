module BankKata.BankAccount where

import           BankKata.Natural
import           Data.List

data Transaction = Deposit Nat | Withdraw Nat deriving (Show, Eq)
newtype BankAccount = BankAccount { getTransactions :: [Transaction] } deriving (Show, Eq)

emptyAccount :: BankAccount
emptyAccount = BankAccount []

deposit :: Nat -> BankAccount -> BankAccount
deposit = flip addTransaction . Deposit

withdraw :: Nat -> BankAccount -> BankAccount
withdraw = flip addTransaction . Withdraw

addTransaction :: BankAccount -> Transaction -> BankAccount
addTransaction account transaction = BankAccount $ (getTransactions account) ++ [transaction]

printStatement :: BankAccount -> String
printStatement account = header ++ "\n" ++ statementLines
  where
    header = " date | credit | debit | balance "
    statementLines = intercalate "\n" $ formatLine <$> zip (reverse transactions) (reverse (runningBalance transactions))
    transactions = getTransactions account

formatLine :: (Transaction, Int) -> String
formatLine (tran, balance) =
  fdate tran ++ sep ++ fcredit tran ++ sep ++ fdebit tran ++ sep ++ fbalance balance ++ " "
  where
    sep = "|"
    fdate tran = (rpad 5 "") ++ " "
    fcredit (Deposit am)  = (rpad 8 ((format am) ++ " "))
    fcredit (Withdraw am) = (rpad 8 "")
    fdebit (Withdraw am) = (rpad 7 ((format am) ++ " "))
    fdebit (Deposit am)  = (rpad 7 "")
    fbalance balance = (rpad 8 (show balance))
    format amount = show $ intValue amount

runningBalance :: [Transaction] -> [Int]
runningBalance trans = drop 1 $ scanl (\b -> \a -> (amountOf a) + b) 0 trans
  where
    amountOf (Deposit am)  = intValue am
    amountOf (Withdraw am) = negate $ intValue am

rpad :: Int -> String -> String
rpad n s
  | length s < n = (++ s ) . concat $ replicate (n - length s) " "
  | otherwise = s
