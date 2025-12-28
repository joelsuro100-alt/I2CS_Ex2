# Ex2: Map2D - Pathfinding & Logic 🗺️

This project focuses on the implementation and management of a 2D map (raster matrix), including graphical capabilities, mathematical operations, and algorithms for pathfinding and area filling.

## 🖼️ Visual Interface (GUI)
The project includes a Graphical User Interface (GUI) based on `StdDraw`, allowing real-time visualization of the map, obstacles, and computed paths.
<img width="1489" height="1198" alt="Screenshot 2025-12-28 210037" src="https://github.com/user-attachments/assets/bd44fe7f-a44d-4fe0-9471-06c0256369fd" />

*(A 60x60 grid map demonstrating the **Shortest Path algorithm** (green line) navigating around a central obstacle (black),
featuring rasterized geometric shapes like a yellow circle and a red square.)*
---

## 🛠️ Key Classes
### 1. `Map.java`
The core class of the project. It implements the `Map2D` interface and represents the map as a 2D array of integers (`int[][]`).
Each cell in the array represents a "pixel" which can be a specific color ID, an obstacle, or part of a path.

**Basic Functions:**
* `init`: Initializes the map (new dimensions, default value, or deep copy from an array).
* `getPixel` / `setPixel`: Access and modify values at specific coordinates.
* `isInside`: Checks if a given point is within the map boundaries.

**Mathematical Operations:**
* `mul(double scalar)`: Multiplies all map values by a scalar. Used for changing shades or logical values.
* `addMap2D`: Adds another map to the current one (element-wise addition).

**Geometric Drawing:**
* `drawRect`, `drawCircle`, `drawLine`: Functions for drawing shapes directly onto the matrix. The implementation includes distance calculations and boundary checks to ensure accurate rasterization.

---

## 🧠 Algorithms
The algorithmic core of the project is based on Graph Theory concepts:

### 🌊 1. Flood Fill
The `fill` function simulates the "Paint Bucket" tool found in drawing software.
* **Goal:** To recolor a connected area of pixels sharing the same original color.
* **Mechanism:** The algorithm uses an **iterative approach** (using a List/Stack) to identify valid neighbors, add them to the processing queue, and color them, continuing until no valid neighbors remain.
* **Feature:** Supports a `cyclic` mode (toroidal world) where the map wraps around the edges (e.g., exiting right enters left).

### 📍 2. Shortest Path
The `shortestPath` function finds the optimal route from point A to point B while bypassing obstacles.
* **Algorithm:** Uses **BFS (Breadth-First Search)**.
* **Why BFS?** In an unweighted graph (like a grid), BFS guarantees finding the shortest path by scanning the map in expanding "waves" (distance 1, then 2, etc.).
* **Process:**
    1.  Create a distance map and mark the start point as 0.
    2.  Propagate to all directions, updating distances (parent distance + 1).
    3.  Maintain a "Parent Map" to track the origin of each step.
    4.  Once the destination is reached, reconstruct the path backwards from End to Start.

---
## 💻 How to Run
1.  Clone the repository.
2.  Open the project in your IDE (IntelliJ / Eclipse).
3.  Run the `Ex2_GUI` class.
    * The program will generate a high-resolution test map (60x60).
    * It will visualize the pathfinding and drawing capabilities shown in the screenshot above.
