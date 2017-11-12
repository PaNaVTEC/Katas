module BankKata.Natural where

newtype Nat = Nat { intValue :: Int } deriving (Show, Eq)

natural :: Int -> Maybe Nat
natural n = if n > 0 then Just $ Nat n else Nothing
