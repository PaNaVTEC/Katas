module Me.Panavtec.Mars.MarsroverSpec (main, spec) where

import           Test.Hspec
import           Test.QuickCheck

import           Me.Panavtec.Mars.Marsrover

-- Develop an api that moves a rover around on a grid.
--
-- Rules:
--
-- You are given the initial starting point (0,0,N) of a rover.
-- 0,0 are X,Y co-ordinates on a grid of (10,10).
-- N is the direction it is facing (i.e. N,S,E,W).
-- L and R allow the rover to rotate left and right.
-- M allows the rover to move one point in the current direction.
-- The rover receives a char array of commands e.g. RMMLM and returns the finishing point after the moves e.g. 2,1,N
-- The rover wraps around if it reaches the end of the grid.
-- The grid may have obstacles. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle e.g. O,2,2,N

main :: IO ()
main = hspec spec

spec :: Spec
spec = do

  describe "Rotations" $ do
    describe "Left" $ do
      it "1 Left" $ do
        moveMars "L" [] `shouldBe` "0,0,W"
      it "2 Left" $ do
        moveMars "LL" [] `shouldBe` "0,0,S"
      it "3 Left" $ do
        moveMars "LLL" [] `shouldBe` "0,0,E"
      it "4 Left" $ do
        moveMars "LLLL" [] `shouldBe` "0,0,N"

    describe "Right" $ do
      it "1 Right" $ do
        moveMars "R" [] `shouldBe` "0,0,E"
      it "2 Right" $ do
        moveMars "RR" [] `shouldBe` "0,0,S"
      it "3 Right" $ do
        moveMars "RRR" [] `shouldBe` "0,0,W"
      it "4 Right" $ do
        moveMars "RRRR" [] `shouldBe` "0,0,N"

    describe "Combinations" $ do
      it "Combining LR" $ do
        moveMars "LR" [] `shouldBe` "0,0,N"

  describe "Movements" $ do
    it "moves one point towards nord" $ do
      moveMars "M" [] `shouldBe` "0,1,N"
    it "moves one point towards south" $ do
      moveMars "LLM" [] `shouldBe` "0,9,S"
    it "moves twice towards nord" $ do
      moveMars "MM" [] `shouldBe` "0,2,N"
    it "moves 1 position in X when facing east" $ do
      moveMars "RM" [] `shouldBe` "1,0,E"
    it "moves 1 position (wrapping up) in X when facing west" $ do
      moveMars "LM" [] `shouldBe` "9,0,W"

  describe "Wraps around if out of the edge" $ do
    it "when moving 10 times in the same direction" $ do
      moveMars "MMMMMMMMMM" [] `shouldBe` "0,0,N"
    it "when moving 10 times in the same direction after going east" $ do
      moveMars "RMMMMMMMMMM" [] `shouldBe` "0,0,E"
    it "when moving 10 times in the same direction after goingwest" $ do
      moveMars "LMMMMMMMMMM" [] `shouldBe` "0,0,W"
    it "when moving 10 times in the same direction after going south" $ do
      moveMars "LLMMMMMMMMMM" [] `shouldBe` "0,0,S"

  describe "Obstacles" $ do
    it "stops moving after finding obstacle" $ do
      moveMars "RMM" [(Coordinate 1 0)] `shouldBe` "O:0,0,E"

