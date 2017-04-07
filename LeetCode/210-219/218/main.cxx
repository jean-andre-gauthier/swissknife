#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <tuple>
#include <vector>

using event = std::tuple<int, int, int, bool>;
class event_comparator {
public:
    int operator () (const event & l, const event & r) const {
        return std::get<2>(l) > std::get<2>(r);
    }
};
using event_queue = std::priority_queue<event, std::vector<event>, event_comparator>;
using event_map = std::map<int, event_queue>;
using active_list_type = std::multiset<event, event_comparator>;
    
int main() {
    
}

class Solution {
private:
    std::tuple<int, event_queue, event_queue> get_next_buildings(event_map& events, active_list_type& active_list) {
        if (events.empty()) {
            return std::make_tuple(-1, event_queue(), event_queue());
        } else {
            auto const & eit = events.begin();
            int current_x = eit->first;
            event_queue queue = eit->second;
            event_queue sbs, ebs;
            
            while (!queue.empty()) {
                auto const e = queue.top(); 
                if (std::get<3>(e)) {
                    event new_event = e;
                    std::get<3>(new_event) = false;
                    events[std::get<1>(new_event)].push(new_event);
                    sbs.push(e);
                } else {
                    ebs.push(e);
                    active_list.erase(e);
                }
            }
            events.erase(current_x);
            
            return std::make_tuple(current_x, sbs, ebs);
        }
    }
    
public:
    std::vector<std::pair<int, int>> getSkyline(std::vector<std::vector<int>>& buildings) {
        event_map events;
        std::vector<std::pair<int, int>> output_buildings;
        
        for (auto it = buildings.begin(); it != buildings.end();) {
            int current_x = (*it)[0];
            event_queue queue;
            
            do {
                queue.push(std::make_tuple((*it)[0], (*it)[1], (*it)[2], true));
                it++;
            } while (current_x == (*it)[0] && it != buildings.end());
            
            events[current_x] = queue;
        }
        
        active_list_type active_list;
        
        for (auto t = get_next_buildings(events, active_list); std::get<0>(t) != -1;) {
            int current_x = std::get<0>(t);
            event_queue sbs = std::get<1>(t);
            event_queue ebs = std::get<2>(t);
            
            if (!sbs.empty() && !ebs.empty()) {
                event hsb = sbs.top();
                event heb = ebs.top();
                
                auto ait = active_list.lower_bound(hsb);
                
                if (ait == active_list.end()) {
                    output_buildings.push_back(std::make_pair(current_x, std::get<2>(hsb)));
                } else {
                    if (active_list.lower_bound(heb) == active_list.end()) {
                        output_buildings.push_back(std::make_pair(current_x, std::get<2>(*ait)));
                    }
                }
            } else if (!sbs.empty()) {
                event hsb = sbs.top();
                
                if (active_list.lower_bound(hsb) == active_list.end()) {
                    output_buildings.push_back(std::make_pair(current_x, std::get<2>(hsb)));
                }
            } else {
                event heb = ebs.top();
                
                if (active_list.lower_bound(heb) == active_list.end()) {
                    auto ait = active_list.begin();
                    
                    if (ait == active_list.end()) {
                        output_buildings.push_back(std::make_pair(current_x, 0));
                    } else {
                        output_buildings.push_back(std::make_pair(current_x, std::get<2>(*ait)));
                    }
                }
            }
        
            while(!sbs.empty()) {
                active_list.insert(sbs.top());
                sbs.pop();
            }
        }
        return output_buildings;
    }
};