module BankKata.BankAccount where

import           BankKata.Natural
import           Data.List
import           Data.Time

data Transaction = Deposit Day Nat | Withdraw Day Nat deriving (Show, Eq)
newtype BankAccount = BankAccount { getTransactions :: [Transaction] } deriving (Show, Eq)

emptyAccount :: BankAccount
emptyAccount = BankAccount []

deposit :: Day -> Nat -> BankAccount -> BankAccount
deposit date amount account = addTransaction account (Deposit date amount)

withdraw :: Day -> Nat -> BankAccount -> BankAccount
withdraw date amount account = addTransaction account (Withdraw date amount)

addTransaction :: BankAccount -> Transaction -> BankAccount
addTransaction account transaction = BankAccount $ (getTransactions account) ++ [transaction]

printStatement :: BankAccount -> String
printStatement account = header ++ "\n" ++ statementLines
  where
    header = "  date | credit | debit | balance "
    statementLines = intercalate "\n" $ formatLine <$> zip (reverse transactions) (reverse (runningBalance transactions))
    transactions = getTransactions account

formatLine :: (Transaction, Int) -> String
formatLine (tran, balance) =
  fdate tran ++ sep ++ fcredit tran ++ sep ++ fdebit tran ++ sep ++ fbalance balance ++ " "
  where
    sep = "|"
    fdate (Deposit day _)  = (rpad 7 ((formatDay day) ++ " "))
    fdate (Withdraw day _) = (rpad 7 ((formatDay day) ++ " "))
    fcredit (Deposit _ am) = (rpad 8 ((formatAmount am) ++ " "))
    fcredit (Withdraw _ _) = (rpad 8 "")
    fdebit (Withdraw _ am) = (rpad 7 ((formatAmount am) ++ " "))
    fdebit (Deposit _ _)   = (rpad 7 "")
    fbalance balance = (rpad 8 (show balance))

formatAmount :: Nat -> String
formatAmount nat = show $ intValue nat

formatDay :: Day -> String
formatDay date = (show d) ++ "-" ++ (show m)
  where (y,m,d) = toGregorian date

runningBalance :: [Transaction] -> [Int]
runningBalance trans = drop 1 $ scanl (\b -> \a -> (amountOf a) + b) 0 trans
  where
    amountOf (Deposit _ am)  = intValue am
    amountOf (Withdraw _ am) = negate $ intValue am

rpad :: Int -> String -> String
rpad n s
  | length s < n = (++ s ) . concat $ replicate (n - length s) " "
  | otherwise = s
