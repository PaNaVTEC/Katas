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

hdate = rpad 7 "date "
hcredit = rpad 8 "credit "
hdebit = rpad 7 "debit "
hbalance = rpad 9 "balance "
sep = "|"

printStatement :: BankAccount -> String
printStatement account = header ++ "\n" ++ statementLines
  where
    header = hdate ++ sep ++ hcredit ++ sep ++ hdebit ++ sep ++ hbalance
    statementLines = intercalate "\n" $ formatLine <$> zip (reverse transactions) (reverse (runningBalance transactions))
    transactions = getTransactions account

formatLine :: (Transaction, Int) -> String
formatLine (tran, balance) =
  fdate tran ++ sep ++ fcredit tran ++ sep ++ fdebit tran ++ sep ++ fbalance balance
  where
    fdate (Deposit day _)  = (rpadto hdate (formatDay day))
    fdate (Withdraw day _) = (rpadto hdate (formatDay day))
    fcredit (Deposit _ am) = (rpadto hcredit (formatAmount am))
    fcredit (Withdraw _ _) = (rpadto hcredit "")
    fdebit (Withdraw _ am) = (rpadto hdebit (formatAmount am))
    fdebit (Deposit _ _)   = (rpadto hdebit "")
    fbalance balance = (rpadto hbalance (show balance))

formatAmount :: Nat -> String
formatAmount nat = show $ intValue nat

formatDay :: Day -> String
formatDay date = (show d) ++ "-" ++ (show m)
  where (_,m,d) = toGregorian date

runningBalance :: [Transaction] -> [Int]
runningBalance trans = drop 1 $ scanl (\b -> \a -> (amountOf a) + b) 0 trans
  where
    amountOf (Deposit _ am)  = intValue am
    amountOf (Withdraw _ am) = negate $ intValue am

rpadto :: String -> String -> String
rpadto l s = rpad (length l) (s ++ " ")

rpad :: Int -> String -> String
rpad n s
  | length s < n = (++ s ) . concat $ replicate (n - length s) " "
  | otherwise = s
