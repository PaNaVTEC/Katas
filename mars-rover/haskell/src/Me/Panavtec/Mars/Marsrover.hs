module Me.Panavtec.Mars.Marsrover where

import           Data.Char
import           Data.Either
import           Data.List
import           Data.Maybe

boardBound :: Int
boardBound = 9

bounds :: [Int]
bounds = cycle([0..boardBound])

data Coordinate = Coordinate Int Int deriving Eq
data Direction = N | W | S | E deriving (Show, Eq)
data Position = Position Coordinate Direction
type Obstacles = [Coordinate]
type FoundObstacle = (Coordinate, Direction)
data Rotate = L | R deriving Eq

leftDirection :: [Direction]
leftDirection = cycle([N, W, S, E])

rightDirection :: [Direction]
rightDirection = cycle([N, E, S, W])

initialPosition :: Position
initialPosition = Position (Coordinate 0 0) N

moveMars :: String -> Obstacles -> String
moveMars orders obstacles = showPosition $ foldl interpret (Left initialPosition) orders
  where interpret (Left position) order
          | order == 'M' = move position obstacles
          | order == 'L' = Left $ rotateLeft position
          | order == 'R' = Left $ rotateRight position
        interpret o _ = o

showPosition :: Either Position FoundObstacle -> String
showPosition p = case (p) of
  (Left (Position (Coordinate x y) d)) -> format x y d
  (Right ((Coordinate x y), d))        -> "O:" ++ format x y d
  where format x y d = intToDigit x: ',' : intToDigit y: ',' : (dd d) : []
        dd d = head $ show d

move :: Position -> Obstacles -> Either Position FoundObstacle
move (Position (Coordinate x y) direction) obstacles = if hasObstacle then Right ((Coordinate x y), direction) else Left (Position (nextCoordinate direction) direction)
  where
    hasObstacle = elem (nextCoordinate direction) obstacles
    nextCoordinate E = Coordinate (increment x) y
    nextCoordinate N = Coordinate x (increment y)
    nextCoordinate W = Coordinate (decrement x) y
    nextCoordinate S = Coordinate x (decrement y)
    increment bound = modify bound (+1)
    decrement bound = modify bound (subtract 1)
    modify bound f = bounds !! noNegatives (f bound)
    noNegatives a
      | a < 0 = boardBound
      | otherwise = a

rotateLeft :: Position -> Position
rotateLeft = rotate L

rotateRight :: Position -> Position
rotateRight = rotate R

rotate :: Rotate -> Position -> Position
rotate direction (Position (Coordinate x y) currentDirection) = Position (Coordinate x y) nextDirection
  where directions
          | direction == L = leftDirection
          | direction == R = rightDirection
        nextDirection = directions !! nextIndex
        currentIndex = elemIndex currentDirection directions
        nextIndex = fromJust(currentIndex) + 1
