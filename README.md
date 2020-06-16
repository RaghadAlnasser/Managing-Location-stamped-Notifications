# Managing-Location-stamped-Notifications

Tired of forgetting to perform your daily tasks, you decide it is time to use technology to help you remember. Instead of using a simple to-do list, however, you want your phone to do the job for you and remind you of your tasks whenever your are near the place where they should be done. For instance, you want to be reminded of submitting the PA whenever your are in college, or buy a new notebook when you visit a specific bookstore. Because you can never predict the exact position where you will be, you want the notification to be sent to you whenever your are in the area surrounding.

Most mobile phones have GPS units that give the phone location to a good precision. The location given is specified by two angles: latitude and longitud.

The class GPS (given to you with this assignment) allows to convert a distance on Earth surface to angles and compute the distance between two points given by latitude and longitude. You may use it if necessary.
Your goal in this assignment is to write the core part of this application, which stores and
1
1. Write a binary search tree implementation of a map, which has both key and data generic. The data structure also supports range search (finding all keys in a given interval).
2. Choose a representation and storage scheme for notifications.
3. Write a class LocNotManager that manipulates the notifications hence represented.
These three steps are detailed in what follows.

1
In this step, you have to write a BST implementation of the interface Map below. Note that both key and data are generic in this interface. In addition to the operations seen in class, there is an important new method that you have to implement, which is getRange(K k1, K k2). This method performs a range search, that is, it returns all elements having a key k such that: k1 less than or equal k less than or equal k2.



2
Representing and storing notifications
)) in average.
boolean remove(K key);
// Return all data in the map in increasing order of the keys. List<Pair<K, T>> getAll();
// Return all elements of the map with
Notifications are represented by the class LocNot (shown below and given to you with the assignment), which contains the following information:
• double lat, lng: Latitude and longitude coordinates in degrees.
• int maxNbRepeats: Some tasks are repetitive (buying milk, for example) whereas other are done once (buying a laptop). The member maxNbRepeats specifies the maximum number of times the task should be repeated. Notifications are sent until the tasks is performed maxNbRepeats times. If maxNbRepeats == 0, the notification is sent every time the phone is near the task location. When the number of repeats is less than the maximum (or maxNbRepeats == 0), the notification is said to be active, otherwise it is inactive.
• int nbRepeats: Actual number of times the task was repeated. This value is incremented by calling the method perform.
• String text: Text of the note. The text does not contain any tabulations, return to line or punctuation marks.


3 Managing notifications
All operations on the notifications map are static methods of the class LocNotManager. These include operations of adding and removing notifications, finding notifications that are active at a certain location, and removing notifications that contain a certain word.
The most important method of the class LocNotManager is getNotsAt, which returns all notifications that are located within a square of side dst centered at the specified location.
Hint: use range search.
